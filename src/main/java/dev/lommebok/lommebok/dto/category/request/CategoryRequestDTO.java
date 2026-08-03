package dev.lommebok.lommebok.dto.category.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {
    @NotBlank(message = "Name the category is obligatory")
    private String name;

    @NotBlank(message = "Color the category is obligatory")
    private String color;
}
