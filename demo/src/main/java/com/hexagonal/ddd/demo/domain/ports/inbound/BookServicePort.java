package com.hexagonal.ddd.demo.domain.ports.inbound;

import com.hexagonal.ddd.demo.domain.models.Book;

import java.util.List;

public interface BookServicePort {

    Book addBook(Book book);

    void removeBook(Long bookId);

    List<Book> getBooks();

}
