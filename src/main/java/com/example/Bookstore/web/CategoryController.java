package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bookstore.model.Category;
import com.example.Bookstore.repositories.CategoryRepository;

import jakarta.validation.Valid;

@Controller
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("categories")
	public String getGategories(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "categories";
	}
	
	@GetMapping("addCategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addCategory";
	}
	
	@PostMapping("saveCategory")
	public String saveCategory(@Valid Category category, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened");
			return "addCategory";
		}
		categoryRepository.save(category);
		return "redirect:categories";

	}

	}

