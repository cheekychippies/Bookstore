package com.example.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.repositories.BookRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	@Autowired
	BookRepository bookRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	
	
	
	@Bean
	public CommandLineRunner demo(BookRepository bookRepository) {return (args) -> {
		
		log.info("LUODAAN DEMODATAA");

		//Book(String title, String author, String isbn, int publicationYear, int price) {
		Book book1 = new Book("Ernest Hemingway", "A Farewell to Arms","123",1929,12); 
		bookRepository.save(book1);
		bookRepository.save(new Book ("George Orwell", "Animal Farm","3456",1945,34));	
		
		log.info("Tulostetaan kirjat");
		for (Book book : bookRepository.findAll()) {
			log.info(book.toString());
		}
	};
	}

}

