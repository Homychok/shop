package com.example.shop.service;
import com.example.shop.dto.CategoryDTO;
import com.example.shop.dto.NewProductDTO;
import com.example.shop.dto.ProductDTO;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.model.Category;
import com.example.shop.model.Product;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.specification.Specification;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return ProductDTO.fromProductToProductDTO(product);
    }

    public List<ProductDTO> searchProduct(String name, String description, String categoryName) {
        return productRepository.findAll(Specification.byName(name)
                        .and(Specification.byDescription(description))
                        .and(Specification.byCategory(categoryName)))
                .stream()
                .map(ProductDTO::fromProductToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(NewProductDTO newProductDTO) {
        Product product = newProductDTO.fromProductDTOToProduct();

        Set<Category> categories = newProductDTO.getCategoriesNames().stream()
                .map(categoryRepository::findByName)
                .collect(Collectors.toSet());

        product.setCategories(categories);
        product.setCreateDate(Instant.now());
        product.setModificateDate(Instant.now());

        productRepository.save(product);

        return ProductDTO.fromProductToProductDTO(product);
    }

    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setModificateDate(Instant.now());

        Set<Category> categories = productDTO.getCategories().stream()
                .map(categoryDTO -> {
                    Category existingCategory = categoryRepository.findByName(categoryDTO.getName());
                    return existingCategory != null ? existingCategory : categoryDTO.fromCategoryDTOToCategory();
                })
                .collect(Collectors.toSet());

        product.setCategories(categories);

        productRepository.save(product);

        return ProductDTO.fromProductToProductDTO(product);

    }

    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            Product productToDelete = product.get();
            productToDelete.getCategories().clear();
            productRepository.delete(productToDelete);
        }
    }

    public List<ProductDTO> findAllProducts(Boolean sort) {
        return productRepository.findAll(
                        sort ?
                                Sort.by(Sort.Direction.DESC, "createDate") :
                                Sort.unsorted())
                .stream()
                .map(ProductDTO::fromProductToProductDTO)
                .collect(Collectors.toList());
    }

//    public CategoryDTO getCategoryByProductId(Long id) {
//        Category category = categoryRepository.findById(getProductById(id).getCategories()).stream()
//                .map(CategoryDTO::fromCategoryToCategoryDTO)
//                .collect(Collectors.toList());;
//        return CategoryDTO.fromCategoryToCategoryDTO(category);
//    }
}
