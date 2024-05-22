package RMS;

import java.util.ArrayList;
import java.util.List;

public class RecipeSearch {
    private RecipeManagementSystem recipeManagementSystem;

    public RecipeSearch(RecipeManagementSystem recipeManagementSystem) {
        this.recipeManagementSystem = recipeManagementSystem;
    }

    public List<Recipe> searchByKeyword(String keyword) {
        List<Recipe> searchResults = new ArrayList<>();
        for (Recipe recipe : recipeManagementSystem.getAllRecipes()) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(recipe);
            }
        }
        return searchResults;
    }

    public List<Recipe> searchByTag(String tag) {
        List<Recipe> searchResults = new ArrayList<>();
        for (Recipe recipe : recipeManagementSystem.getAllRecipes()) {
            if (recipe.getTags().contains(tag.toLowerCase())) {
                searchResults.add(recipe);
            }
        }
        return searchResults;
    }

    public List<Recipe> searchByCategory(String category) {
        List<Recipe> searchResults = new ArrayList<>();
        for (Recipe recipe : recipeManagementSystem.getAllRecipes()) {
            if (recipe.getCategories().contains(category.toLowerCase())) {
                searchResults.add(recipe);
            }
        }
        return searchResults;
    }
}