package com.example.shop.controller;

import com.example.shop.dto.CategoryDTO;
import com.example.shop.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.shop.dto.ProductDTO;
import com.example.shop.dto.NewProductDTO;
import com.example.shop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<ProductDTO> findAllProducts(@RequestParam(defaultValue = "false", value = "sort") Boolean sort) {
        return productService.findAllProducts(sort);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findAllProducts(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(productDTO);
    }
    @GetMapping("/search")
    public List<ProductDTO> search(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String description,
                                   @RequestParam(required = false) String categoryName) {
        return productService.searchProduct(name, description, categoryName);
    }
    @PostMapping
        public ProductDTO create(@RequestBody NewProductDTO newProductDTO) {
            return productService.createProduct(newProductDTO);
        }

    @PostMapping("/{id}")
        public ProductDTO update(@RequestBody ProductDTO productDTO,
                                 @PathVariable Long id) {
                return productService.updateProduct(productDTO, id);
            }

    @DeleteMapping("/{id}")
         public void delete(@PathVariable Long id) {
                 productService.deleteProduct(id);
         }

}
