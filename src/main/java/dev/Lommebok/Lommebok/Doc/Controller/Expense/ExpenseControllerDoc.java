package dev.Lommebok.Lommebok.Doc.Controller.Expense;

import dev.Lommebok.Lommebok.DTO.Expense.Response.ExpenseResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
}
