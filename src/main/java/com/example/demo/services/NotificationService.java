package com.example.demo.services;

import com.example.demo.domain.User;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.toString());
    }
    public void notifyMessage(String message) {
        System.out.println(message);
    }
}
