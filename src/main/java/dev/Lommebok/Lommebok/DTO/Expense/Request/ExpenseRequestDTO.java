package dev.Lommebok.Lommebok.DTO.Expense.Request;

import dev.Lommebok.Lommebok.Enum.Payment.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    public ExpenseRequestDTO() {}

    public ExpenseRequestDTO(String title, String description, BigDecimal amount, LocalDate spentAt, Long categoryId, PaymentType paymentType) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.spentAt = spentAt;
        this.categoryId = categoryId;
        this.paymentType = paymentType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getSpentAt() {
        return spentAt;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setSpentAt(LocalDate spentAt) {
        this.spentAt = spentAt;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
