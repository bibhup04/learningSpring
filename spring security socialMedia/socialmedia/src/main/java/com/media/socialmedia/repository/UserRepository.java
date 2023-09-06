package com.media.socialmedia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.media.socialmedia.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	Optional<User> findByUserName(String userName);
	
	List<User> findAll();
	
	//User findByName(String userName); 
	Integer countByUserName(String name);
	@Query(value = "select * from user where user_name = ?1", nativeQuery = true)
	User findBySomeConstraintSpringCantParse(String name);

}
