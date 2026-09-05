package dev.lommebok.lommebok.controller.expense;

import dev.lommebok.lommebok.service.expense.ExpensePdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/expense")
@Tag(name = "expense")
public class ExpenseExportController {
    private final ExpensePdfService expensePdfService;

    public ExpenseExportController(ExpensePdfService expensePdfService) {
        this.expensePdfService = expensePdfService;
    }

    @GetMapping(value = "/export/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Export expenses as PDF", description = "Downloads all registered expenses with their total.")
    public ResponseEntity<byte[]> exportPdf() throws IOException {
        byte[] pdf = expensePdfService.generatePdf();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
