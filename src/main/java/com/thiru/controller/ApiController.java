package com.thiru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiru.db1.entity.User;
import com.thiru.db2.entity.Book;
import com.thiru.service.DatabaseService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return databaseService.saveUser(user);
    }

    @PostMapping("/book")
    public Book saveBook(@RequestBody Book book) {
        return databaseService.saveBook(book);
    }
}
