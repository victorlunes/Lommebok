package dev.lommebok.lommebok.mapper.expense;

import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.dto.expense.request.ExpenseRequestDTO;
import dev.lommebok.lommebok.dto.expense.response.ExpenseResponseDTO;
import dev.lommebok.lommebok.model.category.CategoryModel;
import dev.lommebok.lommebok.model.expense.ExpenseModel;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ExpenseModel mapToModel(ExpenseRequestDTO expenseRequestDTO, CategoryModel category) {
        ExpenseModel expenseModel = new ExpenseModel();
        expenseModel.setTitle(expenseRequestDTO.getTitle());
        expenseModel.setDescription(expenseRequestDTO.getDescription());
        expenseModel.setAmount(expenseRequestDTO.getAmount());
        expenseModel.setPaymentType(expenseRequestDTO.getPaymentType());
        expenseModel.setSpentAt(expenseRequestDTO.getSpentAt());
        expenseModel.setCategory(category);
        return expenseModel;
    }

    public List<ExpenseResponseDTO> toExpenseListResponse(List<ExpenseModel> expenseModels) {
        return expenseModels.stream()
                .map(expenseModel -> this.mapToDTO(expenseModel))
                .toList();
    }
}