package vn.edu.hust.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.blogapp.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
