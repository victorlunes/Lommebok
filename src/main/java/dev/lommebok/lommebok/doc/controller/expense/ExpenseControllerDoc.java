package dev.lommebok.lommebok.doc.controller.expense;

import dev.lommebok.lommebok.dto.expense.request.ExpenseRequestDTO;
import dev.lommebok.lommebok.dto.expense.response.ExpenseResponseDTO;
import dev.lommebok.lommebok.infra.RestErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(
        name = "expense",
        description = "Endpoint responsible for managing expenses"
)
public interface ExpenseControllerDoc {

    @Operation(
            summary = "List of expenses",
            description = "Endpoint for listing your expenses"
    )
    @ApiResponse(
            responseCode = "200",
            description = "expenses retrieved successfully.",
            content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ExpenseResponseDTO.class)),
                    examples = @ExampleObject(
                            name = "expenses",
                            value = "[{\"id\": 1, \"title\": \"Almoço\", \"description\": \"Restaurante do trabalho\", "
                                    + "\"amount\": 32.90, \"spentAt\": \"2026-07-15T12:30:00\", "
                                    + "\"category\": {\"id\": 1, \"name\": \"Alimentação\", \"color\": \"#FF5733\"}, "
                                    + "\"paymentType\": \"PIX\"}]"
                    )
            )
    )
    @ApiResponse(
            responseCode = "204",
            description = "no expenses found.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "500",
            description = "error retrieving expenses.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "internal error",
                            value = "{\"message\": \"Erro interno inesperado.\", \"status\": \"500 INTERNAL_SERVER_ERROR\"}"
                    )
            )
    )
    ResponseEntity<List<ExpenseResponseDTO>> getExpense();

    @Operation(
            summary = "Create a new expense",
            description = "Endpoint for registering a new expense"
    )
    @ApiResponse(
            responseCode = "201",
            description = "expense created successfully.",
            content = @Content(
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(value = "Expense created successfully")
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid expense payload.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "validation error",
                            value = "{\"message\": \"Erro de validação\", \"status\": \"400 BAD_REQUEST\", "
                                    + "\"errors\": [\"Title is mandatory\", \"Amount is mandatory\"]}"
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "category not found.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "category not found",
                            value = "{\"message\": \"Category not found.\", \"status\": \"404 NOT_FOUND\"}"
                    )
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "error creating expense.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "internal error",
                            value = "{\"message\": \"Erro interno inesperado.\", \"status\": \"500 INTERNAL_SERVER_ERROR\"}"
                    )
            )
    )
    ResponseEntity<String> createExpense(@Valid ExpenseRequestDTO expenseRequestDTO);

    @Operation(
            summary = "Delete Expense",
            description = "Endpoint for delete your expense"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Expense deleted successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "expense not found.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "expense not found",
                            value = "{\"message\": \"Expense not found.\", \"status\": \"404 NOT_FOUND\"}"
                    )
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "error deleting expense.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "internal error",
                            value = "{\"message\": \"Erro interno inesperado.\", \"status\": \"500 INTERNAL_SERVER_ERROR\"}"
                    )
            )
    )
    ResponseEntity<String> deleteExpense(@PathVariable("id") Long id);

    @Operation(
            summary = "Update Expense",
            description = "Endpoint for updating your expense"
    )
    @ApiResponse(
            responseCode = "200",
            description = "expense updated successfully.",
            content = @Content(
                    schema = @Schema(implementation = ExpenseResponseDTO.class),
                    examples = @ExampleObject(
                            name = "expense",
                            value = "{\"id\": 1, \"title\": \"Almoço\", \"description\": \"Restaurante do trabalho\", "
                                    + "\"amount\": 32.90, \"spentAt\": \"2026-07-15T12:30:00\", "
                                    + "\"category\": {\"id\": 1, \"name\": \"Alimentação\", \"color\": \"#FF5733\"}, "
                                    + "\"paymentType\": \"PIX\"}"
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "expense not found.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "expense not found",
                            value = "{\"message\": \"Expense not found.\", \"status\": \"404 NOT_FOUND\"}"
                    )
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "error updating expense.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "internal error",
                            value = "{\"message\": \"Erro interno inesperado.\", \"status\": \"500 INTERNAL_SERVER_ERROR\"}"
                    )
            )
    )
    ResponseEntity<ExpenseResponseDTO> updateExpense(@PathVariable("id") Long id, @RequestBody ExpenseRequestDTO expenseRequestDTO);
}
