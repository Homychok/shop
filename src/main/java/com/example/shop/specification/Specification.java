package com.example.shop.specification;
import com.example.shop.model.Category;
import com.example.shop.model.Product;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
public class Specification {
    public static org.springframework.data.jpa.domain.Specification<Product> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank() || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            root.join("categories", JoinType.LEFT);
            root.fetch("categories");
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");

        };
    }

    public static org.springframework.data.jpa.domain.Specification<Product> byDescription(String desc) {
        return (root, query, criteriaBuilder) -> {
            if (desc == null || desc.isBlank() || desc.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            root.join("categories", JoinType.LEFT);
            root.fetch("categories");
            return criteriaBuilder.like(root.get("description"), "%" + desc + "%");

        };
    }

    public static org.springframework.data.jpa.domain.Specification<Product> byCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isBlank() || category.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Product, Category> categoryJoin = root.join("categories", JoinType.LEFT);
            root.fetch("categories");
            return criteriaBuilder.equal(categoryJoin.get("name"), category);
        };
    }}
