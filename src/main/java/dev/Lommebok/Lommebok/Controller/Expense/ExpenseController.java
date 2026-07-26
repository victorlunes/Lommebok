package dev.Lommebok.Lommebok.Controller.Expense;

import dev.Lommebok.Lommebok.DTO.Expense.Response.ExpenseResponseDTO;
import dev.Lommebok.Lommebok.Doc.Controller.Expense.ExpenseControllerDoc;
import dev.Lommebok.Lommebok.Service.Expense.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        try {

            List<ExpenseResponseDTO> expense = expenseService.getAllExpense();

            if (expense.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }

            return ResponseEntity.status(HttpStatus.OK).body(expense);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
