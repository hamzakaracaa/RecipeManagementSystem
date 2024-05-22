package RMS;

import java.util.*;

public class RecipeManagementSystem {
    private static RecipeManagementSystem instance;
    private Map<String, Recipe> recipes;
    private Deque<Command> commandStack;

    private RecipeManagementSystem() {
        recipes = new HashMap<>();
        commandStack = new ArrayDeque<>();
    }

    public static RecipeManagementSystem getInstance() {
        if (instance == null) {
            instance = new RecipeManagementSystem();
        }
        return instance;
    }

    public void createRecipe(Recipe recipe) {
        recipes.put(recipe.getName(), recipe);
    }

    public void modifyRecipe(String name, Command command) {
        if (recipes.containsKey(name)) {
            command.execute();
            commandStack.push(command);
        } else {
            System.out.println("Recipe not found.");
        }
    }

    public void undoLastModification() {
        if (!commandStack.isEmpty()) {
            Command lastCommand = commandStack.pop();
            lastCommand.undo();
            System.out.println("Last modification undone successfully.");
        } else {
            System.out.println("No modifications to undo.");
        }
    }

    public List<Recipe> searchRecipesByKeyword(String keyword) {
        List<Recipe> searchResults = new ArrayList<>();
        for (Recipe recipe : recipes.values()) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(recipe);
            }
        }
        return searchResults;
    }

    public List<Recipe> searchRecipesByTag(String tag) {
        List<Recipe> searchResults = new ArrayList<>();
        for (Recipe recipe : recipes.values()) {
            if (recipe.getTags().contains(tag.toLowerCase())) {
                searchResults.add(recipe);
            }
        }
        return searchResults;
    }

    public List<Recipe> searchRecipesByCategory(String category) {
        List<Recipe> searchResults = new ArrayList<>();
        for (Recipe recipe : recipes.values()) {
            if (recipe.getCategories().contains(category.toLowerCase())) {
                searchResults.add(recipe);
            }
        }
        return searchResults;
    }

    // Additional method to get all recipes
    public Collection<Recipe> getAllRecipes() {
        return recipes.values();
    }

    // Method to get recipe by name
    public Recipe getRecipeByName(String name) {
        return recipes.get(name);
    }
}