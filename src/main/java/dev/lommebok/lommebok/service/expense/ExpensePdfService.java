package dev.lommebok.lommebok.service.expense;

import dev.lommebok.lommebok.dto.expense.response.ExpenseResponseDTO;
import dev.lommebok.lommebok.mapper.expense.ExpenseMapper;
import dev.lommebok.lommebok.model.expense.ExpenseModel;
import dev.lommebok.lommebok.repository.expense.ExpenseRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import dev.lommebok.lommebok.util.pdf.PdfReportWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class ExpensePdfService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpensePdfService(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    public byte[] generatePdf() throws IOException {

        //as despesas
        List<ExpenseResponseDTO> expenses = returnAllExpenses();

        try (//documento pdf vazio
             var document = new PDDocument();
             // area temporaria onde o pdf vai ser mantido temporariamente
             var output = new ByteArrayOutputStream()
             // os dois recursos vão ser fechados automatiamente no final do try
        ) {
            try (
                    //cria um escritor de PDF usando o documento criado anteriormente, também fechado quando o try acabar
                    var writer = new PdfReportWriter(document)
            ) {
                // Pega o Real R$
                var currency = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

                // Pega a Data
                var date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                writer.line(expenses.size() + " despesas cadastradas", false);
                writer.space();
                writer.tableHeader();

                BigDecimal total = BigDecimal.ZERO;
                if (expenses.isEmpty()) {
                    writer.line("Nenhuma despesa cadastrada.", false);
                }

                // percorre toda despesa da lista
                for (ExpenseResponseDTO expense : expenses) {
                    writeExpense(writer, expense, currency, date);
                    total = total.add(expense.getAmount());
                }
                writer.totalBox("Total: " + currency.format(total));
            }
            //final do try, o writer para automaticamente, para de escrever o conteudo no PDF
            document.save(output);

            //Converte o PDF que está na memória para byte[] e devolve esse conteúdo para o controller.
            return output.toByteArray();
        }
    }

    private void writeExpense(PdfReportWriter writer, ExpenseResponseDTO expense,
                              NumberFormat currency, DateTimeFormatter date) throws IOException {
        writer.expenseRow(
                expense.getTitle(),
                expense.getDescription(),
                expense.getSpentAt() == null ? "-" : date.format(expense.getSpentAt()),
                expense.getCategory() == null ? "-" : expense.getCategory().getName(),
                currency.format(expense.getAmount())
        );
    }

    private List<ExpenseResponseDTO> returnAllExpenses() {

        List<ExpenseModel> expenses = expenseRepository.findAll();

        return expenseMapper.toExpenseListResponse(expenses);
    }
}
