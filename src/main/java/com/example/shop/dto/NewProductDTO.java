package com.example.shop.dto;

import com.example.shop.model.Product;
import lombok.Data;

import java.util.Set;

@Data
public class NewProductDTO {
    private String name;
    private String description;
    private Set<String> categoriesNames;

    public Product fromProductDTOToProduct() {
        Product product = new Product();
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        return product;
    }
}
