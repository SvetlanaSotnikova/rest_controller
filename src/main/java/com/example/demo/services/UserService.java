package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserRepository userRepository;

    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        userRepository.getUsers().add(user);
        notificationService.notifyUser(user);
        return user;
    }
    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
