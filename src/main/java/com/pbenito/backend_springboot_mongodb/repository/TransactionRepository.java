package com.pbenito.backend_springboot_mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pbenito.backend_springboot_mongodb.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
	
	@Query("{transactionId:'?0'}")
	Transaction findTransactionByTransactionId(String transactionId);
	
	@Query(value="{amountEuro:'?0'}", fields="{'transactionId' : 1, 'operationDate' : 1}")
	List<Transaction> findAll(double amountEuro);
	
        @Override
	public long count();
}
