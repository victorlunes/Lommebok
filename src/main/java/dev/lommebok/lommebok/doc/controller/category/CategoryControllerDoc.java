package dev.lommebok.lommebok.doc.controller.category;

import dev.lommebok.lommebok.dto.category.request.CategoryRequestDTO;
import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
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

    @Operation(
            summary = "Create a new category",
            description = "Endpoint for registering a new category"
    )
    @ApiResponse(
            responseCode = "201",
            description = "category created successfully.",
            content = @Content(
                    schema = @Schema(implementation = String.class),
                    examples = @ExampleObject(value = "Category created successfully")
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid category payload.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "validation error",
                            value = "{\"message\": \"Erro de validação\", \"status\": \"400 BAD_REQUEST\", "
                                    + "\"errors\": [\"Name the category is obligatory\", \"Color the category is obligatory\"]}"
                    )
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "error creating category.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "internal error",
                            value = "{\"message\": \"Erro interno inesperado.\", \"status\": \"500 INTERNAL_SERVER_ERROR\"}"
                    )
            )
    )
    ResponseEntity<String> newCategory(@Valid CategoryRequestDTO categoryRequestDTO);
}
