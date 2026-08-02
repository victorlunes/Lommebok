package dev.lommebok.lommebok.mapper.category;

import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.model.category.CategoryModel;
import dev.lommebok.lommebok.model.expense.ExpenseModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryModel mapToModel(CategoryResponseDTO categoryResponseDTO) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(categoryResponseDTO.getId());
        categoryModel.setName(categoryResponseDTO.getName());
        categoryModel.setColor(categoryResponseDTO.getColor());

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
