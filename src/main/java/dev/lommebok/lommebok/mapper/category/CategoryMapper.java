package dev.lommebok.lommebok.mapper.category;

import dev.lommebok.lommebok.dto.category.request.CategoryRequestDTO;
import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.model.category.CategoryModel;
import dev.lommebok.lommebok.model.expense.ExpenseModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryModel mapToModel(CategoryRequestDTO categoryRequestDTO) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(categoryRequestDTO.getName());
        categoryModel.setColor(categoryRequestDTO.getColor());

        return categoryModel;
    }

    public CategoryResponseDTO mapToDTO(CategoryModel categoryModel) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(categoryModel.getId());
        categoryResponseDTO.setName(categoryModel.getName());
        categoryResponseDTO.setColor(categoryModel.getColor());

        return categoryResponseDTO;
    }
}
