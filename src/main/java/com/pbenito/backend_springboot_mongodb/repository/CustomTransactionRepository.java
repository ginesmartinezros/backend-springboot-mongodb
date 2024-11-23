package com.pbenito.backend_springboot_mongodb.repository;

public interface CustomTransactionRepository {
	
	void updateTransactionAmount(String transactionId, double amount);

}
