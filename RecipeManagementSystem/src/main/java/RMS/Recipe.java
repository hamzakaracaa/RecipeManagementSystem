package RMS;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private String cookingInstructions;
    private List<String> categories;
    private List<String> tags;
    private double averageRating;
    private int totalRatings;
    private int servingSize;

    public Recipe(String name, List<String> ingredients, String cookingInstructions, int servingSize) {
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.cookingInstructions = cookingInstructions;
        this.categories = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.averageRating = 0.0;
        this.totalRatings = 0;
        this.servingSize = servingSize;
    }

    // Getters and setters for all attributes

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void addCategory(String category) {
        if (categories.size() < 3) {
            categories.add(category);
        }
    }

    public void removeCategory(String category) {
        categories.remove(category);
    }

    public void addTag(String tag) {
        if (tags.size() < 3) {
            tags.add(tag);
        }
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public void rateRecipe(int rating) {
        totalRatings++;
        averageRating = ((averageRating * (totalRatings - 1)) + rating) / totalRatings;
    }
}