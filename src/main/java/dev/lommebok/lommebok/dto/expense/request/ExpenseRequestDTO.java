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
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequestDTO {
    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Expense date is mandatory")
    private LocalDateTime spentAt;

    @NotNull(message = "Category is mandatory")
    private Long categoryId;

    @NotNull(message = "Payment type is mandatory")
    private PaymentType paymentType;
}
