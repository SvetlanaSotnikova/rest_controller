package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public List<User> userList() {
        return registrationService.getDataProcessingService().getRepository().getUsers();
    }

    //    @GetMapping("/body")
//    public String userAddFromBody() {
////        model.addAttribute("user", new User());
//        return "user-management.html";
//    }
    @GetMapping("/name/{name}")
    public List<User> getUsersByName(@PathVariable String name) {
        List<User> users = registrationService
                .getDataProcessingService()
                .getRepository().getUserByName(name);
        if (users.isEmpty()) {
            throw new RuntimeException("No users found with name: " + name);
        }
        return users; // Возвращаем список пользователей
    }

    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user) {
        registrationService.getDataProcessingService().getRepository().addUser(user);
        return "User added successfully";
    }

//    @GetMapping("/create")
//    public String userAddFromCreate(Model model) {
//        model.addAttribute("user", new User());
//        return "user-management.html";
//    }

//    @PostMapping("/create")
//    public String createUser(@RequestBody  User user) {
//        registrationService.getDataProcessingService().addUserToList(user);
//        return "redirect:/user-management.html";
//    }
}
