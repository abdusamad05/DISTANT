package com.folivora.distant2.controller;


import com.folivora.distant2.domain.Category;
import com.folivora.distant2.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class
CategoryController {


    @Autowired
    private CategoryRepo categoryRepo;
    //Вывод категорий
    @GetMapping("/category")
    public String category(Map<String, Object> model) {
        Iterable<Category> categories = categoryRepo.findAll();

        model.put("categories", categories);

        return "category";
    }
    //Создание категорий
    @PostMapping("/category")
    public String create(@RequestParam String categoryname, Map<String, Object> model){
        Category category = new Category(categoryname);

        categoryRepo.save(category);

        Iterable<Category> categories = categoryRepo.findAll();

        model.put("categories", categories);

        return "category";

    }


}
