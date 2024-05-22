package RMS;

import java.util.ArrayList;
import java.util.List;

public class ChangeIngredientsCommand implements Command {
    private Recipe recipe;
    private List<String> oldIngredients;
    private List<String> newIngredients;

    public ChangeIngredientsCommand(Recipe recipe, List<String> newIngredients) {
        this.recipe = recipe;
        this.oldIngredients = new ArrayList<>(recipe.getIngredients());
        this.newIngredients = newIngredients;
    }

    @Override
    public void execute() {
        recipe.setIngredients(newIngredients);
    }

    @Override
    public void undo() {
        recipe.setIngredients(oldIngredients);
    }
}