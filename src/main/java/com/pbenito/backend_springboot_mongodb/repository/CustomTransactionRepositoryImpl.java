package com.pbenito.backend_springboot_mongodb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.pbenito.backend_springboot_mongodb.model.Transaction;

@Component
public class CustomTransactionRepositoryImpl implements CustomTransactionRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
        @Override
	public void updateTransactionEmail(String name, String email) {
		Query query = new Query(Criteria.where("name").is(name));
		Update update = new Update();
		update.set("quantity", email);
		
		UpdateResult result = mongoTemplate.updateFirst(query, update, Transaction.class);
		
		if(result == null)
			System.out.println("No documents updated");
		else
			System.out.println(result.getModifiedCount() + " document(s) updated..");

	}

}
