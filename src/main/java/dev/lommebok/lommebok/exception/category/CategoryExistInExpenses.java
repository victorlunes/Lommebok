package dev.lommebok.lommebok.exception.category;

public class CategoryExistInExpenses extends RuntimeException {
    public CategoryExistInExpenses() {
        super("This category is associated with existing expenses. Please delete the expenses before deleting the category.");
    }

    public CategoryExistInExpenses(String message) {
        super(message);
    }
}
