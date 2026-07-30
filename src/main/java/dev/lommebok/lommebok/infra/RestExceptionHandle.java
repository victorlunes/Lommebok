package dev.lommebok.lommebok.infra;

import dev.lommebok.lommebok.exception.expense.CategoryNotFoundException;
import dev.lommebok.lommebok.exception.expense.NoExpensesFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestExceptionHandle  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    private ResponseEntity<RestErrorMessage> categoryNotFound(CategoryNotFoundException exception) {
        RestErrorMessage response = new RestErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(NoExpensesFoundException.class)
    private ResponseEntity<Void> noExpensesFound(NoExpensesFoundException exception) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<RestErrorMessage> internalServerError(Exception exception) {
        RestErrorMessage response = new RestErrorMessage(
                "Erro interno inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        List<String> errors = ex.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        RestErrorMessage response = new RestErrorMessage(
                "Erro de validação", HttpStatus.BAD_REQUEST, errors);

        return ResponseEntity.status(status).body(response);
    }
}
