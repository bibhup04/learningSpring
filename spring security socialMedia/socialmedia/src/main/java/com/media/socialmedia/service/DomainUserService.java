package com.media.socialmedia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.media.socialmedia.entity.User;
import com.media.socialmedia.repository.UserRepository;

@Service
public class DomainUserService {

    private static final String ENCODING_STRATEGY = "{bcrypt}";

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    
    public DomainUserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> getByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    private String prefixEncodingStrategyAndEncode(String rawString) {
        return ENCODING_STRATEGY + passwordEncoder.encode(rawString);
    }

    public User save(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(prefixEncodingStrategyAndEncode(password));
        return userRepository.save(user);
    }


}
