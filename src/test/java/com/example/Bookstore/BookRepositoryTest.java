package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.repositories.BookRepository;
import com.example.Bookstore.repositories.CategoryRepository;

@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = bookRepository.findByTitle("Animal Farm");	
		assertThat(books.get(0).getTitle().equals("Animal Farm"));
		
	}

	
	@Test
	public void createNewBook() {
		Book book = new Book("1984","George Orwell","3456",1945,20, categoryRepository.findByName("Horror").get(0));
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		List<Book> books = bookRepository.findByTitle("Animal Farm");
		Book book = books.get(0);
		bookRepository.delete(book);
		List<Book> newBook = bookRepository.findByTitle("Animal Farm");
		assertThat(newBook).hasSize(0);
	}
}
