package com.pbenito.backend_springboot_mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pbenito.backend_springboot_mongodb.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
	
	@Query("{name:'?0'}")
	Transaction findTransactionByName(String name);
	
	@Query(value="{email:'?0'}", fields="{'name' : 1, 'password' : 1}")
	List<Transaction> findAll(String email);
	
        @Override
	public long count();

}
