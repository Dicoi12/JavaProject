package com.example.ImcBeProj.controller;

import com.example.ImcBeProj.models.Category;
import com.example.ImcBeProj.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    public final CategoryRepository categoryRepository;
    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @GetMapping
    public Iterable<Category> getAll(){
        return categoryRepository.findAll();
    }

    @PostMapping("/create")
    public Category create(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable short id) {
    return categoryRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}
}
