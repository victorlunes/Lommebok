package dev.Lommebok.Lommebok.Doc.Controller.Expense;

import dev.Lommebok.Lommebok.DTO.Error.ErrorResponseDTO;
import dev.Lommebok.Lommebok.DTO.Expense.Request.ExpenseRequestDTO;
import dev.Lommebok.Lommebok.DTO.Expense.Response.ExpenseResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Expense",
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
            description = "Error retrieving expenses.",
            content = @Content
    )

    ResponseEntity<List<ExpenseResponseDTO>> getExpense();

    @Operation(
            summary = "Create a new expense",
            description = "Endpoint for registering a new expense"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Expense created successfully."
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid expense payload.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Category not found.",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error creating expense.",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
    )
    ResponseEntity<ExpenseResponseDTO> createExpense(@Valid ExpenseRequestDTO expenseRequestDTO);
}
