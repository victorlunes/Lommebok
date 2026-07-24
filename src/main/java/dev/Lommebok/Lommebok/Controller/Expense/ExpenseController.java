package dev.Lommebok.Lommebok.Controller.Expense;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @GetMapping("/all-expense")
    public String getExpense() {
        return "Expense";
    }
}
