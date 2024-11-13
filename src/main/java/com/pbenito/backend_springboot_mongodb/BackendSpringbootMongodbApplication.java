package com.pbenito.backend_springboot_mongodb;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.pbenito.backend_springboot_mongodb.model.User;
import com.pbenito.backend_springboot_mongodb.repository.CustomUserRepository;
import com.pbenito.backend_springboot_mongodb.repository.UserRepository;


@SpringBootApplication
@EnableMongoRepositories
public class BackendSpringbootMongodbApplication implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CustomUserRepository customRepo;
	
	List<User> userList = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(BackendSpringbootMongodbApplication.class, args);
	}

        @Override
	public void run(String... args){

		//userRepo.deleteAll(); // Doesn't delete the collection
		
		System.out.println("-------------CREATE USER-------------------------------\n");
		
		createUser();
		
		System.out.println("\n----------------SHOW ALL USERS---------------------------\n");
		
		showAllUsers();
		
		System.out.println("\n--------------GET USER BY NAME-----------------------------------\n");
		
		getUserByName("Whole Wheat Biscuit");
		
		System.out.println("\n-----------UPDATE EMAIL OF A USER------------------------\n");
		
		updateUserEmail("sample@email.com");
		
		System.out.println("\n----------DELETE A USER----------------------------------\n");
		
		deleteUser("Kodo Millet");
		
		System.out.println("\n-------------------THANK YOU---------------------------");
	}
	
	// CRUD operations

	//CREATE
	void createUser() {
		System.out.println("Data creation started...");

		userRepo.save(new User("f2j203j9do", "Albert Einstein", "sample@email.com", "1234"));
		
		System.out.println("Data creation complete...");
	}
	
	// READ
	// 1. Show all the data
	 public void showAllUsers() {
		 
		 userList = userRepo.findAll();
		 
		 userList.forEach(user -> System.out.println(getUserDetails(user)));
	 }
	 
	 // 2. Get user by name
	 public void getUserByName(String name) {
		 System.out.println("Getting user by name: " + name);
		 User user = userRepo.findUserByName(name);
		 System.out.println(getUserDetails(user));
	 }

	 // UPDATE APPROACH 1: Using MongoRepository
	 public void updateUserEmail(String email) {
		 
		 // Change to this new value
		 String newEmail = "sample@gmail.com";
		 
		 // Find all the users with the category 
		 List<User> list = userRepo.findAll(email);
		 
		 list.forEach(user -> {
			 // Update the category in each document
			 user.setEmail(newEmail);
		 });
		 
		 // Save all the users in database
		 List<User> usersUpdated = userRepo.saveAll(list);
		 
		 if(usersUpdated != null)
			 System.out.println("Successfully updated " + usersUpdated.size() + " users.");		 
	 }
	 
	 
	 // UPDATE APPROACH 2: Using MongoTemplate
	 public void updateEmail(String name, String email) {
		 System.out.println("Updating quantity for " + name);
		 customRepo.updateUserEmail(name, email);
	 }
	 
	 // DELETE
	 public void deleteUser(String id) {
		 userRepo.deleteById(id);
		 System.out.println("user with id " + id + " deleted...");
	 }
	 // Print details in readable form
	 
	 public String getUserDetails(User user) {

		 System.out.println(
		 "User Name: " + user.getName() + 
		 ", \nUser Email: " + user.getEmail() + 
		 ", \nUser Password: " + user.getPassword()
		 );
		 
		 return "";
	 }
}