package com.hexagonal.ddd.demo.adapters.driving.jpa;

import com.hexagonal.ddd.demo.adapters.driving.jpa.models.BookEntity;
import com.hexagonal.ddd.demo.adapters.driving.jpa.repositories.BookRepository;
import com.hexagonal.ddd.demo.domain.models.Book;
import com.hexagonal.ddd.demo.domain.ports.outbound.BookPersistencePort;

import java.util.List;
import java.util.stream.Collectors;

public class BookJpaAdapter implements BookPersistencePort {

    private final BookRepository bookRepository;


    public BookJpaAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        BookEntity savedBookEntity = this.bookRepository.save(mapToBookEntity(book));
        return mapToBook(savedBookEntity);
    }

    @Override
    public void delete(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll().stream()
                .map(this::mapToBook)
                .collect(Collectors.toList());
    }


    // Could be automated with some framework/library
    private BookEntity mapToBookEntity(Book book) {
        return new BookEntity(
                null,
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getStock()
        );
    }


    private Book mapToBook(BookEntity bookEntity) {
        return Book.builder()
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .pages(bookEntity.getPages())
                .stock(bookEntity.getStock())
                .build();
    }

}
