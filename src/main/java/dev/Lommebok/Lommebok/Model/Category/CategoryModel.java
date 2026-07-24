package dev.Lommebok.Lommebok.Model.Category;

import dev.Lommebok.Lommebok.Model.Expense.ExpenseModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 7)
    private String color;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)

    private List<ExpenseModel> expenses = new ArrayList<>();
}
