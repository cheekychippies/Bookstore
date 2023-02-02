package com.example.Bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Bookstore.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
