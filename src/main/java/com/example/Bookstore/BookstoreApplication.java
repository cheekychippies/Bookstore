package com.example.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.Category;
import com.example.Bookstore.repositories.BookRepository;
import com.example.Bookstore.repositories.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	  
	  

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {return (args) -> {
		
		log.info("LUODAAN DEMODATAA");
		
		categoryRepository.save(new Category("Horror"));
		categoryRepository.save(new Category("Mystery"));
		
	
		//Book(String title, String author, String isbn, int publicationYear, int price) {
		Book book1 = new Book("Ernest Hemingway", "A Farewell to Arms","123",1929,12, categoryRepository.findByName("Mystery").get(0)); 
		bookRepository.save(book1);
		bookRepository.save(new Book ("George Orwell", "Animal Farm","3456",1945,20, categoryRepository.findByName("Horror").get(0)));	
		
		log.info("Tulostetaan kirjat");
		for (Book book : bookRepository.findAll()) {
			log.info(book.toString());
		}
		  
	};  
	

	 
	
	}

}

