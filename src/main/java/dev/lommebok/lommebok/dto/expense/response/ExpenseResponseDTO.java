package dev.lommebok.lommebok.dto.expense.response;

import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.enums.payment.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal amount;
    private LocalDate spentAt;
    private CategoryResponseDTO category;
    private PaymentType paymentType;

    public ExpenseResponseDTO() {}

    public ExpenseResponseDTO(Long id, String title, String description, BigDecimal amount, LocalDate spentAt, CategoryResponseDTO category, PaymentType paymentType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.spentAt = spentAt;
        this.category = category;
        this.paymentType = paymentType;
    }

    public Long getId() {
        return id;
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

    public CategoryResponseDTO getCategory() {
        return category;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCategory(CategoryResponseDTO category) {
        this.category = category;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
