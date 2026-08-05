package dev.lommebok.lommebok.repository.expense;

import dev.lommebok.lommebok.model.expense.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {
    List<ExpenseModel> findAllByCategory_Id(Long categoryId);
}
