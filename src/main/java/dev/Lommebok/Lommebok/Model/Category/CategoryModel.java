package dev.Lommebok.Lommebok.Model.Category;

import dev.Lommebok.Lommebok.Model.Expense.ExpenseModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 7)
    private String color;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<ExpenseModel> expenses = new ArrayList<>();

    public CategoryModel() {}

    public CategoryModel(Long id, String name, String color, List<ExpenseModel> expenses) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.expenses = expenses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<ExpenseModel> getExpenses() {
        return expenses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setExpenses(List<ExpenseModel> expenses) {
        this.expenses = expenses;
    }
}
