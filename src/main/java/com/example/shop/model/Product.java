package com.example.shop.model;

import com.example.shop.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Instant createDate = Instant.now();
    private Instant modificateDate = Instant.now();
    @ManyToMany
//    @JoinTable(name="employee_task",
//            joinColumns=  @JoinColumn(name="employee_id", referencedColumnName="id"),
//            inverseJoinColumns= @JoinColumn(name="task_id", referencedColumnName="id")
    private Set<Category> categories = new HashSet<>();
    public void addCategory(Category category) {
        this.categories.add(category);
    }
}
