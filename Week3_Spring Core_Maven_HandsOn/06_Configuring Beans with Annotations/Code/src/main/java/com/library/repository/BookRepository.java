package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public String Availability(){
        return "Books are Available";
    }
    public String findBookTitle() {
        return "Spring in Action";
    }
    public String Author(){
        return "Shakthi";
    }
}
