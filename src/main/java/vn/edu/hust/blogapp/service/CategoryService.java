package vn.edu.hust.blogapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.hust.blogapp.dto.CategoryDto;
import vn.edu.hust.blogapp.entity.Category;
import vn.edu.hust.blogapp.exception.ResourceNotFoundException;
import vn.edu.hust.blogapp.mapper.CategoryMapper;
import vn.edu.hust.blogapp.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryDto addCategory(CategoryDto categoryDto){
        Category category = CategoryMapper.mapToCategory(categoryDto);
        category = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(category);
    }

    public CategoryDto getCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return CategoryMapper.mapToCategoryDto(category);
    }

    public List<CategoryDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setName(categoryDto.getName());
        category.setId(id);
        category.setDescription(categoryDto.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(updatedCategory);
    }

    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
    }
}
