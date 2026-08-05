package dev.lommebok.lommebok.service.category;

import dev.lommebok.lommebok.dto.category.request.CategoryRequestDTO;
import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.exception.category.CategoryExistInExpenses;
import dev.lommebok.lommebok.exception.category.CategoryNotFoundException;
import dev.lommebok.lommebok.mapper.category.CategoryMapper;
import dev.lommebok.lommebok.model.category.CategoryModel;
import dev.lommebok.lommebok.model.expense.ExpenseModel;
import dev.lommebok.lommebok.repository.category.CategoryRepository;
import dev.lommebok.lommebok.repository.expense.ExpenseRepository;
import dev.lommebok.lommebok.service.expense.ExpenseService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ExpenseRepository expenseRepository;

    public CategoryService(CategoryRepository categoryRepository,  CategoryMapper categoryMapper,  ExpenseRepository expenseRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.expenseRepository = expenseRepository;
    }

    public List<CategoryResponseDTO> getAllCategories() {
        List<CategoryModel> allCategory = categoryRepository.findAll();

        return allCategory.stream().map(c -> categoryMapper.mapToDTO(c)).toList();
    }

    public void createNewCategory(CategoryRequestDTO categoryRequestDTO) {
        CategoryModel newCategory = categoryMapper.mapToModel(categoryRequestDTO);
        categoryRepository.save(newCategory);
    }

    public void categoryUpdate(CategoryRequestDTO categoryRequestDTO, Long idCategory) {
        CategoryModel categoryUpdate = categoryRepository.findById(idCategory)
                .orElseThrow(CategoryNotFoundException::new);

        categoryUpdate.setName(categoryRequestDTO.getName());
        categoryUpdate.setColor(categoryRequestDTO.getColor());
        categoryRepository.save(categoryUpdate);
    }

    public void categoryDelete (Long idCategory) {
        CategoryModel category = categoryRepository.findById(idCategory)
                .orElseThrow(CategoryNotFoundException::new);

        List<ExpenseModel> expensesUsingThisCategory = expenseRepository.findAllByCategory_Id(idCategory);

        if (expensesUsingThisCategory.isEmpty()) {
            categoryRepository.delete(category);
        }else {
            throw new CategoryExistInExpenses();
        }
    }
}
