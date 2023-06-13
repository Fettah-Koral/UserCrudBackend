package com.crudwithkoral.fullstackbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crudwithkoral.fullstackbackend.exception.UserNotFoundException;
import com.crudwithkoral.fullstackbackend.model.User;
import com.crudwithkoral.fullstackbackend.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserConroller {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user")
	public User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("user/{id}")
	public User getUserById(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@PutMapping("user/{id}")
	public User updateUserById(@PathVariable Long id, @RequestBody User newUser) {
		return userRepository.findById(id).map(user -> {
			user.setUserName(newUser.getUserName());
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			return userRepository.save(user);
		}).orElseThrow(() -> new UserNotFoundException(id));
	}

	@DeleteMapping("user/{id}")
	public String deleteUserById(@PathVariable Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		} 

		userRepository.deleteById(id);
		return "User with id "+id+" has been deleted success.";
		

	}

}
