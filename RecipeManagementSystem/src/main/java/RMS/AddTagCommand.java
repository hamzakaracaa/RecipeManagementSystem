package RMS;

import java.util.ArrayList;
import java.util.List;

public class AddTagCommand implements Command {
    private Recipe recipe;
    private List<String> oldTags;
    private String newTag;

    public AddTagCommand(Recipe recipe, String newTag) {
        this.recipe = recipe;
        this.oldTags = new ArrayList<>(recipe.getTags());
        this.newTag = newTag;
    }

    @Override
    public void execute() {
        recipe.addTag(newTag);
    }

    @Override
    public void undo() {
        recipe.setTags(oldTags);
    }
}