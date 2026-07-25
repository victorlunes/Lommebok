package dev.Lommebok.Lommebok.Service.Expense;

import dev.Lommebok.Lommebok.Model.Expense.ExpenseModel;
import dev.Lommebok.Lommebok.Repository.Expense.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<ExpenseModel> getAllExpense() {
        List<ExpenseModel> expense = expenseRepository.findAll();

        return expense;
    }
}
