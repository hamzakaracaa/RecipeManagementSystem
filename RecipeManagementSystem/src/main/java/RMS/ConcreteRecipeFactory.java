// Updated RecipeFactory.java
package RMS;

import java.util.List;

public class ConcreteRecipeFactory extends RecipeFactory {
    @Override
    public Recipe createRecipe(String name, List<String> ingredients, String cookingInstructions, int servingSize) {
        return new Recipe(name, ingredients, cookingInstructions, servingSize);
    }
}