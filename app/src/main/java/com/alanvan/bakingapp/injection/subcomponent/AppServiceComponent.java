package com.alanvan.bakingapp.injection.subcomponent;

import com.alanvan.bakingapp.network.endpoints.RecipeEndPoint;
import com.alanvan.bakingapp.repository.RepositoryManager;

public interface AppServiceComponent {

    RepositoryManager repositoryManager();

    RecipeEndPoint recipeEndPoint();
}
