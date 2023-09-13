package com.learning.restapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.restapi.entity.User;
import com.learning.restapi.exception.CycleShopBusinessException;
import com.learning.restapi.repository.UserRepository;

@Service
public class UserService {

	    @Autowired
	    private UserRepository userRepository;
	
	    public Optional<User> authenticate(String username, String password) {
	        Optional<User> optUser = userRepository.findByName(username);
	        if (optUser.isEmpty()) {
	            throw new CycleShopBusinessException("User not found");
	        }
	        if (!optUser.get().getPassword().equals(password)) {
	            return Optional.empty();
	        }
	        return optUser;
	    }
	
	    public User create(User user) {
	        return userRepository.save(user);
	    }
    
}
