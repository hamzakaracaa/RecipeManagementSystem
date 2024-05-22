package RMS;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create recipe management system instance
        RecipeManagementSystem recipeManagementSystem = RecipeManagementSystem.getInstance();

        // Create recipe factory instance
        RecipeFactory recipeFactory = new ConcreteRecipeFactory();

        // Create scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Main menu loop
        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to Recipe Management System!");
            System.out.println("1. Create Recipe");
            System.out.println("2. Search Recipe");
            System.out.println("3. Rate Recipe");
            System.out.println("4. Modify Recipe");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        // Create Recipe
                        createRecipe(scanner, recipeManagementSystem, recipeFactory);
                        break;
                    case 2:
                        // Search Menu
                        searchMenu(scanner, recipeManagementSystem);
                        break;
                    case 3:
                        // Rate Recipe
                        rateRecipe(scanner, recipeManagementSystem);
                        break;
                    case 4:
                        // Modify Recipe
                        modifyRecipe(scanner, recipeManagementSystem);
                        break;
                    case 5:
                        // Exit
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    // Method to handle recipe creation
    private static void createRecipe(Scanner scanner, RecipeManagementSystem recipeManagementSystem, RecipeFactory recipeFactory) {
        System.out.print("Enter recipe name: ");
        String name = scanner.nextLine();
        System.out.print("Enter serving size: ");
        int servingSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter ingredients (comma-separated): ");
        String ingredientsInput = scanner.nextLine();
        List<String> ingredients = Arrays.asList(ingredientsInput.split(","));
        System.out.print("Enter cooking instructions: ");
        String cookingInstructions = scanner.nextLine();
        Recipe recipe = recipeFactory.createRecipe(name, ingredients, cookingInstructions, servingSize);
        // Add categories
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter category " + (i + 1) + " (or leave blank to skip): ");
            String category = scanner.nextLine();
            if (!category.isEmpty()) {
                recipe.addCategory(category);
            }
        }
        // Add tags
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter tag " + (i + 1) + " (or leave blank to skip): ");
            String tag = scanner.nextLine();
            if (!tag.isEmpty()) {
                recipe.addTag(tag);
            }
        }
        recipeManagementSystem.createRecipe(recipe);
        System.out.println("Recipe created successfully!");
    }

    // Method to handle search menu
    private static void searchMenu(Scanner scanner, RecipeManagementSystem recipeManagementSystem) {
        boolean back = false;
        while (!back) {
            System.out.println("\nSearch Menu");
            System.out.println("1. Search Recipes by Keyword");
            System.out.println("2. Search Recipes by Tag");
            System.out.println("3. Search Recipes by Category");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int searchChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (searchChoice) {
                case 1:
                    // Search Recipes by Keyword
                    searchRecipes(scanner, recipeManagementSystem, "keyword");
                    break;
                case 2:
                    // Search Recipes by Tag
                    searchRecipes(scanner, recipeManagementSystem, "tag");
                    break;
                case 3:
                    // Search Recipes by Category
                    searchRecipes(scanner, recipeManagementSystem, "category");
                    break;
                case 4:
                    // Back to Main Menu
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    // Method to handle searching recipes by keyword, tag, or category
    private static void searchRecipes(Scanner scanner, RecipeManagementSystem recipeManagementSystem, String searchType) {
        System.out.print("Enter " + searchType + " to search: ");
        String searchTerm = scanner.nextLine();
        RecipeSearch recipeSearch = new RecipeSearch(recipeManagementSystem);
        List<Recipe> searchResults;
        if (searchType.equals("keyword")) {
            searchResults = recipeSearch.searchByKeyword(searchTerm);
        } else if (searchType.equals("tag")) {
            searchResults = recipeSearch.searchByTag(searchTerm);
        } else {
            searchResults = recipeSearch.searchByCategory(searchTerm);
        }
        printSearchResults(searchResults);
        if (!searchResults.isEmpty()) {
            System.out.print("Enter the number of the recipe to view details (or 0 to go back): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            if (choice >= 1 && choice <= searchResults.size()) {
                Recipe chosenRecipe = searchResults.get(choice - 1);
                displayRecipeDetails(chosenRecipe);
            } else if (choice != 0) {
                System.out.println("Invalid choice.");
            }
        }
    }

    // Method to display detailed information about a recipe
    private static void displayRecipeDetails(Recipe recipe) {
        System.out.println("\nRecipe Details:");
        System.out.println("Name: " + recipe.getName());
        System.out.println("Ingredients: " + String.join(", ", recipe.getIngredients()));
        System.out.println("Tags: " + String.join(", ", recipe.getTags()));
        System.out.println("Categories: " + String.join(", ", recipe.getCategories()));
        System.out.println("Serving Size: " + recipe.getServingSize());
        System.out.println("Instructions: " + recipe.getCookingInstructions());
        System.out.println("Average Rating: " + recipe.getAverageRating());
        System.out.println("Total Ratings: " + recipe.getTotalRatings());
    }

    // Method to handle recipe rating
    private static void rateRecipe(Scanner scanner, RecipeManagementSystem recipeManagementSystem) {
        System.out.print("Enter keyword to search for recipe: ");
        String keyword = scanner.nextLine();
        RecipeSearch recipeSearch = new RecipeSearch(recipeManagementSystem);
        List<Recipe> searchResults = recipeSearch.searchByKeyword(keyword);
        printSearchResults(searchResults);
        if (!searchResults.isEmpty()) {
            System.out.print("Enter the number of the recipe to rate (or 0 to cancel): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            if (choice >= 1 && choice <= searchResults.size()) {
                Recipe chosenRecipe = searchResults.get(choice - 1);
                System.out.print("Enter rating (1-5): ");
                int rating = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                RecipeRatingModule ratingModule = new RecipeRatingModule(recipeManagementSystem);
                ratingModule.rateRecipe(chosenRecipe, rating);
                System.out.println("Rating submitted successfully!");
            } else if (choice != 0) {
                System.out.println("Invalid choice.");
            }
        }
    }

    // Method to handle modifying a recipe
    private static void modifyRecipe(Scanner scanner, RecipeManagementSystem recipeManagementSystem) {
        System.out.print("Enter keyword to search for recipe to modify: ");
        String keyword = scanner.nextLine();
        RecipeSearch recipeSearch = new RecipeSearch(recipeManagementSystem);
        List<Recipe> searchResults = recipeSearch.searchByKeyword(keyword);
        printSearchResults(searchResults);
        if (!searchResults.isEmpty()) {
            System.out.print("Enter the number of the recipe to modify (or 0 to cancel): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            if (choice >= 1 && choice <= searchResults.size()) {
                Recipe chosenRecipe = searchResults.get(choice - 1);
                // Modify Menu
                boolean back = false;
                while (!back) {
                    System.out.println("\nModify Menu");
                    System.out.println("1. Change Ingredients");
                    System.out.println("2. Change Cooking Instructions");
                    System.out.println("3. Add Category");
                    System.out.println("4. Add Tag");
                    System.out.println("5. Undo Last Modification");
                    System.out.println("6. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    int modifyChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    switch (modifyChoice) {
                        case 1:
                            // Change Ingredients
                            changeIngredients(scanner, recipeManagementSystem, chosenRecipe);
                            break;
                        case 2:
                            // Change Cooking Instructions
                            changeCookingInstructions(scanner, recipeManagementSystem, chosenRecipe);
                            break;
                        case 3:
                            // Add Category
                            addCategory(scanner, recipeManagementSystem, chosenRecipe);
                            break;
                        case 4:
                            // Add Tag
                            addTag(scanner, recipeManagementSystem, chosenRecipe);
                            break;
                        case 5:
                            // Undo Last Modification
                            recipeManagementSystem.undoLastModification();
                            break;
                        case 6:
                            // Back to Main Menu
                            back = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    }
                }
            } else if (choice != 0) {
                System.out.println("Invalid choice.");
            }
        }
    }

    // Method to change ingredients of a recipe
    private static void changeIngredients(Scanner scanner, RecipeManagementSystem recipeManagementSystem, Recipe recipe) {
        System.out.print("Enter new ingredients (comma-separated): ");
        String ingredientsInput = scanner.nextLine();
        List<String> newIngredients = Arrays.asList(ingredientsInput.split(","));
        Command command = new ChangeIngredientsCommand(recipe, newIngredients);
        recipeManagementSystem.modifyRecipe(recipe.getName(), command);
        System.out.println("Ingredients changed successfully!");
    }

    // Method to change cooking instructions of a recipe
    private static void changeCookingInstructions(Scanner scanner, RecipeManagementSystem recipeManagementSystem, Recipe recipe) {
        System.out.print("Enter new cooking instructions: ");
        String newCookingInstructions = scanner.nextLine();
        Command command = new ChangeCookingInstructionsCommand(recipe, newCookingInstructions);
        recipeManagementSystem.modifyRecipe(recipe.getName(), command);
        System.out.println("Cooking instructions changed successfully!");
    }

    // Method to add category to a recipe
    private static void addCategory(Scanner scanner, RecipeManagementSystem recipeManagementSystem, Recipe recipe) {
        System.out.print("Enter category to add: ");
        String category = scanner.nextLine();
        Command command = new AddCategoryCommand(recipe, category);
        recipeManagementSystem.modifyRecipe(recipe.getName(), command);
        System.out.println("Category added successfully!");
    }

    // Method to add tag to a recipe
    private static void addTag(Scanner scanner, RecipeManagementSystem recipeManagementSystem, Recipe recipe) {
        System.out.print("Enter tag to add: ");
        String tag = scanner.nextLine();
        Command command = new AddTagCommand(recipe, tag);
        recipeManagementSystem.modifyRecipe(recipe.getName(), command);
        System.out.println("Tag added successfully!");
    }

    // Helper method to print search results
    private static void printSearchResults(List<Recipe> results) {
        System.out.println("Search results:");
        if (results.isEmpty()) {
            System.out.println("No recipes found.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i).getName());
            }
        }
    }
}