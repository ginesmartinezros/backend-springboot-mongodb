package com.pbenito.backend_springboot_mongodb;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.pbenito.backend_springboot_mongodb.model.Transaction;
import com.pbenito.backend_springboot_mongodb.repository.CustomTransactionRepository;
import com.pbenito.backend_springboot_mongodb.repository.TransactionRepository;


@SpringBootApplication
@EnableMongoRepositories
public class BackendSpringbootMongodbApplication implements CommandLineRunner{
	
	@Autowired
	TransactionRepository transactionRepo;
	
	@Autowired
	CustomTransactionRepository customRepo;
	
	List<Transaction> transactionList = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(BackendSpringbootMongodbApplication.class, args);
	}

        @Override
	public void run(String... args){

		//transactionRepo.deleteAll(); // Doesn't delete the collection
		
		System.out.println("-------------CREATE USER-------------------------------\n");
		
		createTransaction();
		
		System.out.println("\n----------------SHOW ALL USERS---------------------------\n");
		
		showAllTransactions(); //TODO caution, this can return too much transactions
		
		System.out.println("\n--------------GET USER BY NAME-----------------------------------\n");
		
		getTransactionByName("Albert Einstein");
		
		System.out.println("\n-----------UPDATE EMAIL OF A USER------------------------\n");
		
		updateTransactionEmail("sample@email.com");
		
		System.out.println("\n----------DELETE A USER----------------------------------\n");
		
		deleteTransaction("f2j203j9do");
		
		System.out.println("\n-------------------THANK YOU---------------------------");
	}
	
	// CRUD operations

	//CREATE
	void createTransaction() {
		System.out.println("Data creation started...");

		transactionRepo.save(new Transaction("f2j203j9do", "Albert Einstein", "sample@email.com", "1234"));
		
		System.out.println("Data creation complete...");
	}
	
	// READ
	// 1. Show all the data
	 public void showAllTransactions() {
		 
		 transactionList = transactionRepo.findAll();
		 
		 transactionList.forEach(transaction -> System.out.println(getTransactionDetails(transaction)));
	 }
	 
	 // 2. Get transaction by name
	 public void getTransactionByName(String name) {
		 System.out.println("Getting transaction by name: " + name);
		 Transaction transaction = transactionRepo.findTransactionByName(name);
		 System.out.println(getTransactionDetails(transaction));
	 }

	 // UPDATE APPROACH 1: Using MongoRepository
	 public void updateTransactionEmail(String email) {
		 
		 // Change to this new value
		 String newEmail = "sample@gmail.com";
		 
		 // Find all the transactions with the category 
		 List<Transaction> list = transactionRepo.findAll(email);
		 
		 list.forEach(transaction -> {
			 // Update the category in each document
			 transaction.setEmail(newEmail);
		 });
		 
		 // Save all the transactions in database
		 List<Transaction> transactionsUpdated = transactionRepo.saveAll(list);
		 
		 if(transactionsUpdated != null)
			 System.out.println("Successfully updated " + transactionsUpdated.size() + " transactions.");		 
	 }
	 
	 
	 // UPDATE APPROACH 2: Using MongoTemplate
	 public void updateEmail(String name, String email) {
		 System.out.println("Updating quantity for " + name);
		 customRepo.updateTransactionEmail(name, email);
	 }
	 
	 // DELETE
	 public void deleteTransaction(String id) {
		 transactionRepo.deleteById(id);
		 System.out.println("transaction with id " + id + " deleted...");
	 }
	 // Print details in readable form
	 
	 public String getTransactionDetails(Transaction transaction) {

		 System.out.println(
		 "Transaction Name: " + transaction.getName() + 
		 ", \nTransaction Email: " + transaction.getEmail() + 
		 ", \nTransaction Password: " + transaction.getPassword()
		 );
		 
		 return "";
	 }
}