package com.pbenito.backend_springboot_mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pbenito.backend_springboot_mongodb.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
	
	@Query("{transactionId:'?0'}")
	Transaction findTransactionByTransactionId(String transactionId);
	
	@Query(value="{transactionId:'?0'}", fields="{'amountEuro' : 1, 'operationDate' : 1}")
	List<Transaction> findAll(String transactionId);
	
        @Override
	public long count();

}
