package com.example.Bookstore.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.Bookstore.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	List<Category> findByName(String name);
}
