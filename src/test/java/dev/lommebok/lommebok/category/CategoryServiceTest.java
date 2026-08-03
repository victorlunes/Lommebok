package dev.lommebok.lommebok.category;

import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.mapper.category.CategoryMapper;
import dev.lommebok.lommebok.model.category.CategoryModel;
import dev.lommebok.lommebok.repository.category.CategoryRepository;
import dev.lommebok.lommebok.service.category.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    @DisplayName("Must return all categories existing in the database.")
    void returnAllCategories() {
        CategoryModel category = new CategoryModel(1L, "Alimentação", "#FF0000", List.of());
        CategoryResponseDTO categoryDTO = new CategoryResponseDTO(1L, "Alimentação", "#FF0000");

        given(categoryRepository.findAll()).willReturn(List.of(category));
        given(categoryMapper.mapToDTO(category)).willReturn(categoryDTO);

        List<CategoryResponseDTO> allCategories = categoryService.getAllCategories();

        assertEquals(1, allCategories.size());
        assertEquals("Alimentação", allCategories.get(0).getName());
        assertEquals("#FF0000", allCategories.get(0).getColor());
    }

    @Test
    @DisplayName("Must return an empty list when there are no categories in the database.")
    void returnEmptyListWhenNoCategoriesExistInDatabase() {
        given(categoryRepository.findAll()).willReturn(List.of());

        List<CategoryResponseDTO> allCategories = categoryService.getAllCategories();

        assertEquals(0, allCategories.size());
        assertTrue(allCategories.isEmpty());
    }
}