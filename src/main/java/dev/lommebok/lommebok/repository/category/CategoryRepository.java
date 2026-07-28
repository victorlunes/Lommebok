package dev.lommebok.lommebok.repository.category;

import dev.lommebok.lommebok.model.category.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
