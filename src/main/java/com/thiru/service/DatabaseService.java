package com.thiru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiru.db1.entity.User;
import com.thiru.db1.repository.UserRepository;
import com.thiru.db2.entity.Book;
import com.thiru.db2.repository.BookRepository;

@Service
public class DatabaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}