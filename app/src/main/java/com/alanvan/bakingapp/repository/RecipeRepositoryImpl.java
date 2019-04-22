package com.alanvan.bakingapp.repository;

import com.alanvan.bakingapp.datasource.DataSource;
import com.alanvan.bakingapp.datasource.LocalDataSource;
import com.alanvan.bakingapp.datasource.RemoteDataSource;
import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;
import com.alanvan.bakingapp.utils.CacheHelper;
import com.alanvan.bakingapp.utils.RxUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class RecipeRepositoryImpl implements RecipeRepository {

    private final DataSource remoteDataSource;
    private final DataSource localDataSource;
    private final CacheHelper cacheHelper;

    @Inject
    public RecipeRepositoryImpl() {
        remoteDataSource = RemoteDataSource.getInstance();
        localDataSource = LocalDataSource.getInstance();
        cacheHelper = Injector.getContextComponent().cacheHelper();
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return Observable.fromCallable(cacheHelper::isDataSynced).flatMap(isDataSynced -> {
            if (isDataSynced) {
                return localDataSource.getRecipes();
            } else {
                return remoteDataSource.getRecipes().doOnNext(recipeList -> {
                    localDataSource.clearLocalData()
                            .flatMap(d -> localDataSource.saveRecipes(recipeList))
                            .compose(RxUtils.applyIOSchedulers())
                            .subscribe(result -> {
                                Logger.d("Success saving recipes");
                                cacheHelper.setDataSynced(true);
                            }, error -> {
                                throw new Exception("Error saving recipes");
                            });
                });
            }
        });
    }

    @Override
    public Observable<Recipe> getRecipe(int recipeId) {
        return Observable.fromCallable(cacheHelper::isDataSynced).flatMap(isDataSynced -> {
            if (isDataSynced) {
                return localDataSource.getRecipe(recipeId);
            } else {
                return remoteDataSource.getRecipe(recipeId)
                        .doOnNext(recipe
                        -> localDataSource.saveRecipe(recipe).compose(RxUtils.applyIOSchedulers())
                        .subscribe(result -> {
                            Logger.d("Success saving recipe");
                        }, error -> {
                            throw new Exception("Error saving recipe");
                        }));
            }
        });
    }

    @Override
    public Observable<Step> getStep(int recipeId, int stepId) {
        return getRecipe(recipeId).map(recipe -> {
            Step step = new Step();
            List<Step> steps = recipe.getSteps();
            for (Step s : steps) {
                if (s.getId() == stepId) {
                    step = s;
                }
            }
            return step;
        });
    }

    @Override
    public Observable<Integer> getLastStepId(int recipeId) {
        return getRecipe(recipeId).map(recipe -> recipe.getSteps().size() - 1);
    }
}
