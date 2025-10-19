package vn.edu.hust.blogapp.mapper;

import vn.edu.hust.blogapp.dto.CategoryDto;
import vn.edu.hust.blogapp.entity.Category;

public class CategoryMapper {
    public static CategoryDto mapToCategoryDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    public static Category mapToCategory(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }
}
