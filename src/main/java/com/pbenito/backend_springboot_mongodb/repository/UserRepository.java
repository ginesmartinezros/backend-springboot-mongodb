package com.pbenito.backend_springboot_mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pbenito.backend_springboot_mongodb.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	@Query("{name:'?0'}")
	User findUserByName(String name);
	
	@Query(value="{email:'?0'}", fields="{'name' : 1, 'password' : 1}")
	List<User> findAll(String email);
	
        @Override
	public long count();

}
