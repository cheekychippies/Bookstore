package com.example.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.repositories.BookRepository;
import com.example.Bookstore.repositories.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepository; 
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("booklist")
	public String showBooks(Model model) {
		model.addAttribute("books",bookRepository.findAll());
		return "booklist";
	} 
	@GetMapping("addBook")
	public String newBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "newBook";  
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("edit/{id}")
	public String editBook(@PathVariable("id")Long id, Model model ) {
		model.addAttribute("book" , bookRepository.findById(id));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editBook";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model){ 
		bookRepository.deleteById(id);
	 return "redirect:/booklist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("saveBook") 
	public String saveBook(Book book) {
		bookRepository.save(book); 
		return "redirect:/booklist";   
	}
	//Rest
	/*
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest(){
		return(List<Book>) bookRepository.findAll();
		
	}
	@GetMapping("/books/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id")Long bookId){
		return bookRepository.findById(bookId);
		
	}
	@PostMapping("/books")
	public @ResponseBody List<Book> newBookRest(@RequestBody Book newBook) {
		return bookRepository.save(newBook);
		
	}*/
	
}   
