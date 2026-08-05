package dev.lommebok.lommebok.controller.category;

import dev.lommebok.lommebok.doc.controller.category.CategoryControllerDoc;
import dev.lommebok.lommebok.dto.category.request.CategoryRequestDTO;
import dev.lommebok.lommebok.dto.category.response.CategoryResponseDTO;
import dev.lommebok.lommebok.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerDoc {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all-category")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @PostMapping("/new-category")
    public ResponseEntity<String> newCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        categoryService.createNewCategory(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully");
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") Long idCategory, @Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        categoryService.categoryUpdate(categoryRequestDTO, idCategory);
        return  ResponseEntity.status(HttpStatus.OK).body("Category updated successfully");
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long idCategory) {
        categoryService.categoryDelete(idCategory);
        return  ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully");
    }
}
