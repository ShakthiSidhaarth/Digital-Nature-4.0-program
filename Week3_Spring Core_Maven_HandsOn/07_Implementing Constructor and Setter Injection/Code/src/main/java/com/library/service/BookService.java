package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private String libraryName;
    private BookRepository bookRepository;
    public BookService(String libraryName) {
        this.libraryName = libraryName;
        System.out.println("Constructor Injection: libraryName = " + libraryName);
    }
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("Setter Injection: BookRepository set.");
    }
    public void displayInfo() {
        System.out.println("Library Name: " + libraryName);
        System.out.println("Book Available: " + bookRepository.getBookTitle());
    }
}
