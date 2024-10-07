package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private NotificationService notification;

    public UserRepository getRepository() {
        return repository;
    }

    public List<User> sortUserByAge(List<User> users) {
        notification.notifyMessage("sorted by age");
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public double calculateAverageAge(List<User> users) {
        notification.notifyMessage("calculated by age");
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    public List<User> filterUserByAge(List<User> users, int age) {
        notification.notifyMessage("filtered by age");
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public void addUserToList(User user) {
        notification.notifyMessage("adding user");
        repository.addUser(user);
    }
}
