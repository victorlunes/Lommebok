package dev.Lommebok.Lommebok.Service.Expense;

import dev.Lommebok.Lommebok.DTO.Expense.Request.ExpenseRequestDTO;
import dev.Lommebok.Lommebok.DTO.Expense.Response.ExpenseResponseDTO;
import dev.Lommebok.Lommebok.Mapper.Expense.ExpenseMapper;
import dev.Lommebok.Lommebok.Model.Category.CategoryModel;
import dev.Lommebok.Lommebok.Model.Expense.ExpenseModel;
import dev.Lommebok.Lommebok.Repository.Category.CategoryRepository;
import dev.Lommebok.Lommebok.Repository.Expense.ExpenseRepository;
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
