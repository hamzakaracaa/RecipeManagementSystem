package RMS;

public class ChangeCookingInstructionsCommand implements Command {
    private Recipe recipe;
    private String oldCookingInstructions;
    private String newCookingInstructions;

    public ChangeCookingInstructionsCommand(Recipe recipe, String newCookingInstructions) {
        this.recipe = recipe;
        this.oldCookingInstructions = recipe.getCookingInstructions();
        this.newCookingInstructions = newCookingInstructions;
    }

    @Override
    public void execute() {
        recipe.setCookingInstructions(newCookingInstructions);
    }

    @Override
    public void undo() {
        recipe.setCookingInstructions(oldCookingInstructions);
    }
}