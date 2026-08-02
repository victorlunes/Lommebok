package dev.lommebok.lommebok.doc.controller.category;

import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.infra.RestErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "category",
        description = "Endpoint responsible for managing categories"
)
public interface CategoryControllerDoc {

    @Operation(
            summary = "List of categories",
            description = "Endpoint for listing all categories"
    )
    @ApiResponse(
            responseCode = "200",
            description = "categories retrieved successfully.",
            content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = CategoryResponseDTO.class)),
                    examples = @ExampleObject(
                            name = "categories",
                            value = "[{\"id\": 1, \"name\": \"Alimentação\", \"color\": \"#FF5733\"}]"
                    )
            )
    )
    @ApiResponse(
            responseCode = "204",
            description = "no categories found.",
            content = @Content
    )
    @ApiResponse(
            responseCode = "500",
            description = "error retrieving categories.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "internal error",
                            value = "{\"message\": \"Erro interno inesperado.\", \"status\": \"500 INTERNAL_SERVER_ERROR\"}"
                    )
            )
    )
    ResponseEntity<List<CategoryResponseDTO>> getAllCategories();
}
