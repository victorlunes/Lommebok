package dev.Lommebok.Lommebok.Controller.Expense;

import dev.Lommebok.Lommebok.Model.Expense.ExpenseModel;
import dev.Lommebok.Lommebok.Service.Expense.ExpenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/all-expense")
    public List<ExpenseModel> getExpense() {
        List<ExpenseModel> expense = expenseService.getAllExpense();
        return expense;
    }
}
