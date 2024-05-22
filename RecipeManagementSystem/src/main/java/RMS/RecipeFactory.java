package RMS;

import java.util.List;

public abstract class RecipeFactory {
    public abstract Recipe createRecipe(String name, List<String> ingredients, String cookingInstructions, int servingSize);
}