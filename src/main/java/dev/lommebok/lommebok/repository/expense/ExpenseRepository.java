package dev.lommebok.lommebok.repository.expense;

import dev.lommebok.lommebok.model.expense.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {
}
