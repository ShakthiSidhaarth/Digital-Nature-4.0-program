package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;


    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void getBookDetails(int id) {
        String book = bookRepository.findBookById(id);
        System.out.println(book);
    }
}
