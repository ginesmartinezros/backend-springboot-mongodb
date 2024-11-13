package com.pbenito.backend_springboot_mongodb.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pbenito.backend_springboot_mongodb.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	@Query("{name:'?0'}")
	User findItemByName(String name);
	
	@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
	List<User> findAll(String category);
	
	public long count();

}
