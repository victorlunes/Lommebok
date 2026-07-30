package dev.lommebok.lommebok.dto.expense.request;

import dev.lommebok.lommebok.enums.payment.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequestDTO {
    @NotBlank(message = "O título é obrigatório")
    private String title;

    private String description;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal amount;

    @NotNull(message = "A data da despesa é obrigatória")
    private LocalDate spentAt;

    @NotNull(message = "A categoria é obrigatória")
    private Long categoryId;

    @NotNull(message = "O tipo de pagamento é obrigatório")
    private PaymentType paymentType;
}
