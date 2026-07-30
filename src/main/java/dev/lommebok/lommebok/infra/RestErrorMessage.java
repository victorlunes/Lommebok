package dev.lommebok.lommebok.infra;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestErrorMessage {
    private String message;
    private HttpStatus status;
    private List<String> errors;

    public RestErrorMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
