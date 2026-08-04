package dev.lommebok.lommebok.dto.expense.response;

import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.enums.payment.PaymentType;
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
public class ExpenseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal amount;
    private LocalDateTime spentAt;
    private CategoryResponseDTO category;
    private PaymentType paymentType;
}
