package com.hexagonal.ddd.demo.domain.service;

import com.hexagonal.ddd.demo.domain.models.Book;
import com.hexagonal.ddd.demo.domain.ports.inbound.BookServicePort;
import com.hexagonal.ddd.demo.domain.ports.outbound.BookPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookServicePort {

    private final BookPersistencePort bookPersistencePort;


    public BookService(BookPersistencePort bookPersistencePort) {
        this.bookPersistencePort = bookPersistencePort;
    }

    @Override
    public Book addBook(Book book) {
        return this.bookPersistencePort.save(book);
    }

    @Override
    public void removeBook(Long bookId) {
        this.bookPersistencePort.delete(bookId);
    }

    @Override
    public List<Book> getBooks() {
        return this.bookPersistencePort.findAll();
    }
}
