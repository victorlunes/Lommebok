package dev.lommebok.lommebok.doc.controller.expense;

import dev.lommebok.lommebok.dto.error.ErrorResponseDTO;
import dev.lommebok.lommebok.dto.expense.request.ExpenseRequestDTO;
import dev.lommebok.lommebok.dto.expense.response.ExpenseResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

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
            responseCode = "200"
    )
    @ApiResponse(
            responseCode = "204",
            description = "There are no expenses at the moment.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "500",
            description = "error retrieving expenses.",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
    )

    ResponseEntity<List<ExpenseResponseDTO>> getExpense();

    @Operation(
            summary = "Create a new expense",
            description = "Endpoint for registering a new expense"
    )
    @ApiResponse(
            responseCode = "201",
            description = "expense created successfully."
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid expense payload.",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "category not found.",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
    )
    @ApiResponse(
            responseCode = "500",
            description = "error creating expense.",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
    )
    ResponseEntity<ExpenseResponseDTO> createExpense(@Valid ExpenseRequestDTO expenseRequestDTO);
}
