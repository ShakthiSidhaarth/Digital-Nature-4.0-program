package com.library.service;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    public void getAllBooks() {
        System.out.println("Fetching all books...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void display(){
        System.out.println("All Books were Fetching by Shakthi....");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
