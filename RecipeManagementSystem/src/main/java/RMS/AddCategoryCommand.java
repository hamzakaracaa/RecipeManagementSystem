package RMS;

import java.util.ArrayList;
import java.util.List;

public class AddCategoryCommand implements Command {
    private Recipe recipe;
    private List<String> oldCategories;
    private String newCategory;

    public AddCategoryCommand(Recipe recipe, String newCategory) {
        this.recipe = recipe;
        this.oldCategories = new ArrayList<>(recipe.getCategories());
        this.newCategory = newCategory;
    }

    @Override
    public void execute() {
        recipe.addCategory(newCategory);
    }

    @Override
    public void undo() {
        recipe.setCategories(oldCategories);
    }
}