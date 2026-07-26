package dev.Lommebok.Lommebok.Repository.Expense;

import dev.Lommebok.Lommebok.Model.Expense.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {
}
