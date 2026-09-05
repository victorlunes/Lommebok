package dev.lommebok.lommebok.infra;

import dev.lommebok.lommebok.exception.user.UsernameOrPasswordInvalidException;
import dev.lommebok.lommebok.exception.category.CategoryExistInExpenses;
import dev.lommebok.lommebok.exception.category.CategoryNotFoundException;
import dev.lommebok.lommebok.exception.expense.ExpenseNotFoundException;
import dev.lommebok.lommebok.exception.expense.NoExpensesFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandle {

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleNotFoundException(UsernameOrPasswordInvalidException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<RestErrorMessage> categoryNotFound(CategoryNotFoundException exception) {
        RestErrorMessage response = new RestErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(NoExpensesFoundException.class)
    public ResponseEntity<Void> noExpensesFound(NoExpensesFoundException exception) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<RestErrorMessage> expenseNotFound(ExpenseNotFoundException exception) {
        RestErrorMessage response = new RestErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CategoryExistInExpenses.class)
    public ResponseEntity<RestErrorMessage> categoryExistInExpenses(CategoryExistInExpenses exception) {
        RestErrorMessage response = new RestErrorMessage(exception.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> internalServerError(Exception exception) {
        RestErrorMessage response = new RestErrorMessage(
                "Erro interno inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        List<String> errors = ex.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        RestErrorMessage response = new RestErrorMessage(
                "Erro de validação", HttpStatus.BAD_REQUEST, errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
