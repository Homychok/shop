package com.example.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.shop.model.Category;
import lombok.Data;

@Data
public class CategoryDTO {

    @JsonIgnore
    private Long id;
    private String name;


    public static CategoryDTO fromCategoryToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public Category fromCategoryDTOToCategory() {
        Category category = new Category();
        category.setId(this.getId());
        category.setName(this.getName());
        return category;
    }
}
