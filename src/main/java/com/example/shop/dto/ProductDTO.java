package com.example.shop.dto;

import com.example.shop.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Data
public class ProductDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant modificateDate;
    private Set<CategoryDTO> categories = new HashSet<>();


    public static ProductDTO fromProductToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setCreateDate(product.getCreateDate());
        productDTO.setModificateDate(product.getModificateDate());
        productDTO.setCategories(product.getCategories().stream().map(CategoryDTO::fromCategoryToCategoryDTO).collect(toSet()));
        return productDTO;
    }

    public Product fromProductDTOToProduct() {
        Product product = new Product();
        product.setId(this.getId());
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setCreateDate(this.getCreateDate());
        product.setModificateDate(this.getModificateDate());
        product.setCategories(this.getCategories().stream().map(CategoryDTO::fromCategoryDTOToCategory).collect(toSet()));
        return product;
    }
}
