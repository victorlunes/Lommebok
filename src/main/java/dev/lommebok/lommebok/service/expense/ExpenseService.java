package dev.lommebok.lommebok.service.expense;

import dev.lommebok.lommebok.dto.expense.request.ExpenseRequestDTO;
import dev.lommebok.lommebok.dto.expense.response.ExpenseResponseDTO;
import dev.lommebok.lommebok.mapper.expense.ExpenseMapper;
import dev.lommebok.lommebok.model.category.CategoryModel;
import dev.lommebok.lommebok.model.expense.ExpenseModel;
import dev.lommebok.lommebok.repository.category.CategoryRepository;
import dev.lommebok.lommebok.repository.expense.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;
    private ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.expenseMapper = expenseMapper;
    }

    public List<ExpenseResponseDTO> getAllExpense() {
        List<ExpenseModel> expense = expenseRepository.findAll();

        return expense.stream().map(e -> expenseMapper.mapToDTO(e)).toList();
    }

    public ExpenseResponseDTO createNewExpense(ExpenseRequestDTO expenseRequestDTO) {
        CategoryModel category = categoryRepository.findById(expenseRequestDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        ExpenseModel expenseModel = expenseMapper.mapToModel(expenseRequestDTO, category);
        ExpenseModel savedExpense = expenseRepository.save(expenseModel);

        return expenseMapper.mapToDTO(savedExpense);
    }
}
