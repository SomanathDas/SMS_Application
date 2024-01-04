package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.User;
import com.sms.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    public User authenticateUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public boolean authorizeUser(String username, String role) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getRole().equals(role);
    }
    
    public void saveOrUpdateUser(int userId, String username, String password, String role) {
        // Check if the user already exists
        User existingUser = userRepository.findByUsername(username);

        if (existingUser == null) {
            // If the user does not exist, create a new user
            User newUser = new User();
            newUser.setUserId(userId);
            newUser.setUsername(username);
            newUser.setPassword(password); // Note: In a real application, you should hash the password
            newUser.setRole(role);
            userRepository.save(newUser);
        } else {
            // If the user exists, update the password
            existingUser.setPassword(password); // Note: In a real application, you should hash the password
            userRepository.save(existingUser);
        }
    }
}
