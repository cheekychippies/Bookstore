package com.example.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.model.ApplicationUser;
import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.Category;
import com.example.Bookstore.repositories.ApplicationUserRepository;
import com.example.Bookstore.repositories.BookRepository;
import com.example.Bookstore.repositories.CategoryRepository;



import java.util.Arrays;

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
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository,
			ApplicationUserRepository applicationUserRepository) {return (args) -> {
		
		log.info("LUODAAN DEMODATAA");
		
		ApplicationUser user1 = new ApplicationUser("Hemmo", "Hemmonen", "USER", "user",
				"$2a$10$zpNTpMKOfqnMLaxtqMeHHemGotX0Tvt4hNCyP3f2aLCK7L2HwLDHa");
		
		ApplicationUser user2 = new ApplicationUser("Keijo", "Keijonen", "ADMIN", "admin",
				"$2a$10$i5MnSUwJs5/2Fu/fJr4ei.2x.pn3ARtjFEoBwIewL2C1/rHi4p4Kq");
		
		applicationUserRepository.saveAll(Arrays.asList(user1, user2));
		
		categoryRepository.save(new Category("Horror"));
		categoryRepository.save(new Category("Mystery"));
		
	
		//Book(String title, String author, String isbn, int publicationYear, int price) {
		Book book1 = new Book("A Farewell to Arms","Ernest Hemingway","123",1929,12, categoryRepository.findByName("Mystery").get(0)); 
		bookRepository.save(book1);
		bookRepository.save(new Book ("Animal Farm","George Orwell","3456",1945,20, categoryRepository.findByName("Horror").get(0)));	
		
		log.info("Tulostetaan kirjat");
		for (Book book : bookRepository.findAll()) {
			log.info(book.toString());
		}
		  
	};  
	

	 
	
	}

}

