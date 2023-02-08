package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.repositories.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepository; 
	
	@GetMapping("booklist")
	public String showBooks(Model model) {
		model.addAttribute("books",bookRepository.findAll());
		return "booklist";
	} 
	@GetMapping("addBook")
	public String newBook(Model model) {
		model.addAttribute("book", new Book());
		return "newBook";  
	}
	@GetMapping("edit/{id}")
	public String editBook(@PathVariable("id")Long id, Model model ) {
		model.addAttribute("book" , bookRepository.findById(id));
		return "editBook";
	}
	@GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model){ 
		bookRepository.deleteById(id);
	 return "redirect:/booklist";
	}
	  
	@PostMapping("saveBook")
	public String saveBook(Book book) {
		bookRepository.save(book); 
		return "redirect:/booklist";   
	}
	
}   
