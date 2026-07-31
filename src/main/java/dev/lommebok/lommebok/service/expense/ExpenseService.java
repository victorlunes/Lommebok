package dev.lommebok.lommebok.service.expense;

import dev.lommebok.lommebok.dto.expense.request.ExpenseRequestDTO;
import dev.lommebok.lommebok.dto.expense.response.ExpenseResponseDTO;
import dev.lommebok.lommebok.exception.category.CategoryNotFoundException;
import dev.lommebok.lommebok.exception.expense.ExpenseNotFoundException;
import dev.lommebok.lommebok.exception.expense.NoExpensesFoundException;
import dev.lommebok.lommebok.mapper.expense.ExpenseMapper;
import dev.lommebok.lommebok.model.category.CategoryModel;
import dev.lommebok.lommebok.model.expense.ExpenseModel;
import dev.lommebok.lommebok.repository.category.CategoryRepository;
import dev.lommebok.lommebok.repository.expense.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.expenseMapper = expenseMapper;
    }

    public List<ExpenseResponseDTO> getAllExpense() {
        List<ExpenseModel> expense = expenseRepository.findAll();

        if (expense.isEmpty()) {
            throw new NoExpensesFoundException();
        }

        return expense.stream().map(e -> expenseMapper.mapToDTO(e)).toList();
    }

    public void createNewExpense(ExpenseRequestDTO expenseRequestDTO) {
        CategoryModel category = categoryRepository.findById(expenseRequestDTO.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        ExpenseModel expenseModel = expenseMapper.mapToModel(expenseRequestDTO, category);
        ExpenseModel savedExpense = expenseRepository.save(expenseModel);

        expenseMapper.mapToDTO(savedExpense);
    }

    public void deleteExpense(Long id) {
        ExpenseModel findExpense = expenseRepository.findById(id)
                .orElseThrow(ExpenseNotFoundException::new);

        expenseRepository.delete(findExpense);
    }

    public ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO expenseRequestDTO) {
        ExpenseModel findExpense = expenseRepository.findById(id)
                .orElseThrow(ExpenseNotFoundException::new);

        findExpense.setTitle(expenseRequestDTO.getTitle());
        findExpense.setDescription(expenseRequestDTO.getDescription());
        findExpense.setAmount(expenseRequestDTO.getAmount());
        findExpense.setPaymentType(expenseRequestDTO.getPaymentType());
        findExpense.setSpentAt(expenseRequestDTO.getSpentAt());
        findExpense.getCategory().setId(expenseRequestDTO.getCategoryId());

        return expenseMapper.mapToDTO(expenseRepository.save(findExpense));
    }
}
