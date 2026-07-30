package dev.lommebok.lommebok.exception.expense;

public class NoExpensesFoundException extends RuntimeException {
    public NoExpensesFoundException() {
        super("No expenses found.");
    }

    public NoExpensesFoundException(String message) {
        super(message);
    }
}
