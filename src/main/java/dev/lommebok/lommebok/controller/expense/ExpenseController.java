package dev.lommebok.lommebok.controller.expense;

import dev.lommebok.lommebok.dto.expense.request.ExpenseRequestDTO;
import dev.lommebok.lommebok.dto.expense.response.ExpenseResponseDTO;
import dev.lommebok.lommebok.doc.controller.expense.ExpenseControllerDoc;
import dev.lommebok.lommebok.service.expense.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController implements ExpenseControllerDoc {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/all-expense")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpense() {
        List<ExpenseResponseDTO> expense = expenseService.getAllExpense();

        if (expense.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }

    @PostMapping("/new-expense")
    public ResponseEntity<ExpenseResponseDTO> createExpense(@Valid @RequestBody ExpenseRequestDTO expenseRequestDTO) {
        ExpenseResponseDTO createdExpense = expenseService.createNewExpense(expenseRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }
}
