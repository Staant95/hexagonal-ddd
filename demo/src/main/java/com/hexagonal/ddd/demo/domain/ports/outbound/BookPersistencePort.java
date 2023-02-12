package com.hexagonal.ddd.demo.domain.ports.outbound;

import com.hexagonal.ddd.demo.domain.models.Book;

import java.util.List;

public interface BookPersistencePort {

    Book save(Book book);

    void delete(Long bookId);

    List<Book> findAll();

}
