package com.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.repository.BookRepository;
@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void Available(){
        System.out.println(bookRepository.Availability());
    }
    public void displayBookTitle() {
        System.out.println("Book: " + bookRepository.findBookTitle());
    }
    public void Author(){
        System.out.println("Author:"+bookRepository.Author());
    }
}
