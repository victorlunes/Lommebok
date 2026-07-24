package dev.Lommebok.Lommebok.Model.Expense;

import dev.Lommebok.Lommebok.Enum.Payment.PaymentType;
import dev.Lommebok.Lommebok.Model.Category.CategoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lommebok_expense")
@AllArgsConstructor
@NoArgsConstructor
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
}
