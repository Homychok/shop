package com.example.shop.controller;

import com.example.shop.dto.CategoryDTO;
import com.example.shop.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
//    @GetMapping("{id}/products")
//    public ResponseEntity<Collection<ProductDTO>> getProductsByCategoryId (@PathVariable Long id) {
//        return ResponseEntity.ok(categoryService.getProductsByCategoryId(id));
//    }

}
