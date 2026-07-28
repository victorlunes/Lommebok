package dev.lommebok.lommebok.dto.error;

import java.util.List;

public class ErrorResponseDTO {
    private String message;
    private List<FieldErrorDTO> errors;

    public ErrorResponseDTO() {}

    public ErrorResponseDTO(String message) {
        this.message = message;
    }

    public ErrorResponseDTO(String message, List<FieldErrorDTO> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<FieldErrorDTO> getErrors() {
        return errors;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrors(List<FieldErrorDTO> errors) {
        this.errors = errors;
    }
}
