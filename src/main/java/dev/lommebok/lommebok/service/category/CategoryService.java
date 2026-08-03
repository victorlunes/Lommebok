package dev.lommebok.lommebok.service.category;

import dev.lommebok.lommebok.dto.category.request.CategoryRequestDTO;
import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.mapper.category.CategoryMapper;
import dev.lommebok.lommebok.model.category.CategoryModel;
import dev.lommebok.lommebok.repository.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository,  CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryResponseDTO> getAllCategories() {
        List<CategoryModel> allCategory = categoryRepository.findAll();

        return allCategory.stream().map(c -> categoryMapper.mapToDTO(c)).toList();
    }

    public void createNewCategory(CategoryRequestDTO categoryRequestDTO) {
        CategoryModel newCategory = categoryMapper.mapToModel(categoryRequestDTO);
        categoryRepository.save(newCategory);
    }
}
