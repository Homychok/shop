package com.example.shop.service;

import com.example.shop.dto.CategoryDTO;
import com.example.shop.dto.ProductDTO;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.exception.handlers.IncorrectControllerAdvice;
import com.example.shop.model.Product;
import com.example.shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::fromCategoryToCategoryDTO)
                .collect(Collectors.toList());
    }
//    public Collection<ProductDTO> getProductsByCategoryId(Long id) {
//        List<Product> products = categoryRepository.findById(id).get().getProducts();
//        List<ProductDTO> productDTOS = new ArrayList<>();
//        for(Product product : products) {
//            ProductDTO productDTO = ProductDTO.fromProductToProductDTO(product);
//            productDTOS.add(productDTO);
//        }
//        return productDTOS;
//    }

}
