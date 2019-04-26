package com.alanvan.bakingapp.ui.widget;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Pair;
import android.widget.RemoteViews;

import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.utils.RxUtils;
import com.alanvan.bakingapp.utils.StringUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

public class WidgetProvider extends AppWidgetProvider {

    private WidgetDataProvider widgetDataProvider = new WidgetDataProvider();


    @SuppressLint("CheckResult")
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int appWidgetId: appWidgetIds) {
            Pair<Integer, String> recipeIdAndName = widgetDataProvider.getRecipeIdAndNameFromPreferences(appWidgetId);

            widgetDataProvider.getIngredientList(recipeIdAndName.first)
                    .compose(RxUtils.applyIOSchedulers())
                    .subscribe(ingredients -> updateAppWidget(context, appWidgetManager, appWidgetId, recipeIdAndName.second, ingredients),
                            error -> Logger.v("Failed to update widget data"));
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    public void updateAppWidget(Context context, AppWidgetManager manager, int appWidgetId, String recipeName, List<Ingredient> ingredientList) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient_list);
        views.setTextViewText(R.id.widgetRecipeName, recipeName);
        views.removeAllViews(R.id.widgetIngredientListContainer);

        for (Ingredient ingredient: ingredientList) {
            RemoteViews ingredientView = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient_item);
            String ingredientLine = StringUtils.formatIngredientLine(ingredient);
            ingredientView.setTextViewText(R.id.widgetIngredientName, ingredientLine);

            views.addView(R.id.widgetIngredientListContainer, ingredientView);
        }

        manager.updateAppWidget(appWidgetId, views);
    }
}
