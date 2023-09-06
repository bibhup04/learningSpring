package com.media.socialmedia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.media.socialmedia.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	User findByUserName(String userName);
	
	List<User> findAll();

}
