package dev.Lommebok.Lommebok.Repository.Category;

import dev.Lommebok.Lommebok.Model.Category.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
