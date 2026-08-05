package dev.lommebok.lommebok.category;

import dev.lommebok.lommebok.dto.category.request.CategoryRequestDTO;
import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.exception.category.CategoryExistInExpenses;
import dev.lommebok.lommebok.exception.category.CategoryNotFoundException;
import dev.lommebok.lommebok.mapper.category.CategoryMapper;
import dev.lommebok.lommebok.model.category.CategoryModel;
import dev.lommebok.lommebok.model.expense.ExpenseModel;
import dev.lommebok.lommebok.repository.category.CategoryRepository;
import dev.lommebok.lommebok.repository.expense.ExpenseRepository;
import dev.lommebok.lommebok.service.category.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private ExpenseRepository expenseRepository;

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

    @Test
    @DisplayName("Must map the request DTO to a CategoryModel and save it when creating a new category.")
    void mustCreateNewCategorySuccessfully() {
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("Alimentação", "#FF0000");
        CategoryModel mappedCategory = new CategoryModel(null, "Alimentação", "#FF0000", List.of());

        given(categoryMapper.mapToModel(categoryRequestDTO)).willReturn(mappedCategory);

        categoryService.createNewCategory(categoryRequestDTO);

        verify(categoryRepository).save(mappedCategory);
    }

    @Test
    @DisplayName("Must update name and color of an existing category.")
    void mustUpdateCategorySuccessfully() {
        CategoryModel existingCategory = new CategoryModel(1L, "Alimentação", "#FF0000", List.of());
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("Lazer", "#00FF00");

        given(categoryRepository.findById(1L)).willReturn(Optional.of(existingCategory));

        categoryService.categoryUpdate(categoryRequestDTO, 1L);

        ArgumentCaptor<CategoryModel> categoryCaptor = ArgumentCaptor.forClass(CategoryModel.class);
        verify(categoryRepository).save(categoryCaptor.capture());

        CategoryModel savedCategory = categoryCaptor.getValue();
        assertEquals(1L, savedCategory.getId());
        assertEquals("Lazer", savedCategory.getName());
        assertEquals("#00FF00", savedCategory.getColor());
    }

    @Test
    @DisplayName("Must throw CategoryNotFoundException when updating a category that does not exist.")
    void mustThrowCategoryNotFoundExceptionWhenCategoryDoesNotExist() {
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("Lazer", "#00FF00");

        given(categoryRepository.findById(99L)).willReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.categoryUpdate(categoryRequestDTO, 99L));

        verify(categoryRepository, never()).save(any());
    }

    @Test
    @DisplayName("Must keep the category id unchanged after an update.")
    void mustKeepCategoryIdUnchangedAfterUpdate() {
        CategoryModel existingCategory = new CategoryModel(5L, "Alimentação", "#FF0000", List.of());
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("Lazer", "#00FF00");

        given(categoryRepository.findById(5L)).willReturn(Optional.of(existingCategory));

        categoryService.categoryUpdate(categoryRequestDTO, 5L);

        ArgumentCaptor<CategoryModel> categoryCaptor = ArgumentCaptor.forClass(CategoryModel.class);
        verify(categoryRepository).save(categoryCaptor.capture());

        assertEquals(5L, categoryCaptor.getValue().getId());
    }

    @Test
    @DisplayName("Must delete a category when it has no expenses associated with it.")
    void mustDeleteCategorySuccessfully() {
        CategoryModel existingCategory = new CategoryModel(1L, "Alimentação", "#FF0000", List.of());

        given(categoryRepository.findById(1L)).willReturn(Optional.of(existingCategory));
        given(expenseRepository.findAllByCategory_Id(1L)).willReturn(List.of());

        categoryService.categoryDelete(1L);

        verify(categoryRepository).delete(existingCategory);
    }

    @Test
    @DisplayName("Must throw CategoryNotFoundException when deleting a category that does not exist.")
    void mustThrowCategoryNotFoundExceptionWhenDeletingCategoryThatDoesNotExist() {
        given(categoryRepository.findById(99L)).willReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.categoryDelete(99L));

        verify(categoryRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Must throw CategoryExistInExpenses and not delete the category when it has expenses associated with it.")
    void mustThrowCategoryExistInExpensesWhenCategoryHasExpenses() {
        CategoryModel existingCategory = new CategoryModel(1L, "Alimentação", "#FF0000", List.of());
        ExpenseModel expense = new ExpenseModel();

        given(categoryRepository.findById(1L)).willReturn(Optional.of(existingCategory));
        given(expenseRepository.findAllByCategory_Id(1L)).willReturn(List.of(expense));

        assertThrows(CategoryExistInExpenses.class,
                () -> categoryService.categoryDelete(1L));

        verify(categoryRepository, never()).delete(any());
    }
}