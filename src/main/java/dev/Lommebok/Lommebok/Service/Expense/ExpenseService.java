package dev.Lommebok.Lommebok.Service.Expense;

import dev.Lommebok.Lommebok.DTO.Expense.Response.ExpenseResponseDTO;
import dev.Lommebok.Lommebok.Mapper.Expense.ExpenseMapper;
import dev.Lommebok.Lommebok.Model.Expense.ExpenseModel;
import dev.Lommebok.Lommebok.Repository.Expense.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseRepository expenseRepository,  ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = new ExpenseMapper();
    }

    public List<ExpenseResponseDTO> getAllExpense() {
        List<ExpenseModel> expense = expenseRepository.findAll();

        return expense.stream().map(e -> expenseMapper.mapToDTO(e)).toList();
    }
}
