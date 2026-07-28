package dev.lommebok.lommebok.model.expense;

import dev.lommebok.lommebok.enums.payment.PaymentType;
import dev.lommebok.lommebok.model.category.CategoryModel;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lommebok_expense")
public class ExpenseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "spent_at", nullable = false)
    private LocalDate spentAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    public ExpenseModel() {}

    public ExpenseModel(Long id, String title, String description, BigDecimal amount, LocalDate spentAt, CategoryModel category, PaymentType paymentType) {
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

    public CategoryModel getCategory() {
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

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
