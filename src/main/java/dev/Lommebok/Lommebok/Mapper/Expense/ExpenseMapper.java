package dev.Lommebok.Lommebok.Mapper.Expense;

import dev.Lommebok.Lommebok.DTO.Category.Response.CategoryResponseDTO;
import dev.Lommebok.Lommebok.DTO.Expense.Response.ExpenseResponseDTO;
import dev.Lommebok.Lommebok.Model.Category.CategoryModel;
import dev.Lommebok.Lommebok.Model.Expense.ExpenseModel;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public ExpenseResponseDTO mapToDTO(ExpenseModel expenseModel) {
        ExpenseResponseDTO expenseResponseDTO = new ExpenseResponseDTO();
        expenseResponseDTO.setId(expenseModel.getId());
        expenseResponseDTO.setTitle(expenseModel.getTitle());
        expenseResponseDTO.setDescription(expenseModel.getDescription());
        expenseResponseDTO.setCategory(mapCategoryToDTO(expenseModel.getCategory()));
        expenseResponseDTO.setAmount(expenseModel.getAmount());
        expenseResponseDTO.setPaymentType(expenseModel.getPaymentType());
        expenseResponseDTO.setSpentAt(expenseModel.getSpentAt());
        return expenseResponseDTO;
    }

    private CategoryResponseDTO mapCategoryToDTO(CategoryModel categoryModel) {
        return new CategoryResponseDTO(categoryModel.getId(), categoryModel.getName(), categoryModel.getColor());
    }
}