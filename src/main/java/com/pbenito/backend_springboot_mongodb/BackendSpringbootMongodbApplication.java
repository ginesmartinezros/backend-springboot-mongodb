package com.pbenito.backend_springboot_mongodb;


import java.util.ArrayList;
import java.util.Date;
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
		/* 
		System.out.println("-------------CREATE USER-------------------------------\n");
		
		createTransaction();
		*/
		System.out.println("\n----------------SHOW ALL USERS---------------------------\n");
		
		showAllTransactions(); //TODO caution, this can return too much transactions
		
		/*

		System.out.println("\n--------------GET USER BY NAME-----------------------------------\n");
		
		getTransactionByTransactionId("170327");
		
		System.out.println("\n-----------UPDATE EMAIL OF A USER------------------------\n");
		
		updateTransactionAmount(0.88888);
		
		System.out.println("\n----------DELETE A USER----------------------------------\n");
		
		deleteTransaction("sdfioashdoin");

		*/
		
		System.out.println("\n-------------------THANK YOU---------------------------");
	}
	
	// CRUD operations

	//CREATE
	void createTransaction() {
		System.out.println("Data creation started...");
		Date currentDate = new Date();
		transactionRepo.save(new Transaction("sdfioashdoin","test shop","sdfasdfa", 
		 "Venta",  0.999,  "21/07/2001", currentDate,"99348", "0000"));
		
		System.out.println("Data creation complete...");
	}
	
	// READ
	// 1. Show all the data
	 public void showAllTransactions() {
		 
		 transactionList = transactionRepo.findAll();
		 
		 transactionList.forEach(transaction -> System.out.println(getTransactionDetails(transaction)));
	 }
	 
	 // 2. Get transaction by name
	 public void getTransactionByTransactionId(String transactionId) {
		 System.out.println("Getting transaction by name: " + transactionId);
		 Transaction transaction = transactionRepo.findTransactionByTransactionId(transactionId);
		 System.out.println(getTransactionDetails(transaction));
	 }

	 // UPDATE APPROACH 1: Using MongoRepository
	 public void updateTransactionAmount(double amount) {
		 
		 // Change to this new value
		 double newAmount = 0.9999;
		 
		 // Find all the transactions with the category 
		 List<Transaction> list = transactionRepo.findAll(newAmount);
		 
		 list.forEach(transaction -> {
			 // Update the category in each document
			 transaction.setAmountEuro(newAmount);
		 });
		 
		 // Save all the transactions in database
		 List<Transaction> transactionsUpdated = transactionRepo.saveAll(list);
		 
		 if(transactionsUpdated != null)
			 System.out.println("Successfully updated " + transactionsUpdated.size() + " transactions. \n");		 
	 }
	 
	 
	 // UPDATE APPROACH 2: Using MongoTemplate
	 public void updateAmount(String transactionId, double amount) {
		 System.out.println("Updating amount");
		 customRepo.updateTransactionAmount(transactionId, amount);
	 }
	 
	 // DELETE
	 public void deleteTransaction(String id) {
		 transactionRepo.deleteById(id);
		 System.out.println("transaction with id " + id + " deleted...");
	 }
	 // Print details in readable form
	 
	 public String getTransactionDetails(Transaction transaction) {
		System.out.println(
			"Shop: " + transaction.getShop() + 
			", \nShop ID: " + transaction.getShopId() + 
			", \nOperation Type: " + transaction.getOperationType() + 
			", \nAmount (EUR): " + transaction.getAmountEuro() + 
			", \nOperation Date (String): " + transaction.getOperationDateString() + 
			", \nOperation Date (ISO): " + transaction.getOperationDate() + 
			", \nTransaction ID: " + transaction.getTransactionId() + 
			", \nLast Four Digits of Credit Card: " + transaction.getLastFourDigitsCreditCard()
		);
		return "Transaction details logged successfully. \n";
	}
}