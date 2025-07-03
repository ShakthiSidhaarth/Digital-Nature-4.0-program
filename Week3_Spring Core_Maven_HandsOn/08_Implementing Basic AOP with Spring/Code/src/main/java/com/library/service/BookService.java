package com.library.service;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    public void addBook() {
        System.out.println("Book added.");
    }
    public void deleteBook() {
        System.out.println("Book deleted.");
    }
}
