package RMS;

public class RecipeRatingModule {
    private RecipeManagementSystem recipeManagementSystem;

    public RecipeRatingModule(RecipeManagementSystem recipeManagementSystem) {
        this.recipeManagementSystem = recipeManagementSystem;
    }

    public void rateRecipe(Recipe recipe, int rating) {
        recipe.rateRecipe(rating);
    }
}