package com.example.Bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Bookstore.model.ApplicationUser;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

	ApplicationUser findByUsername(String username);

}
