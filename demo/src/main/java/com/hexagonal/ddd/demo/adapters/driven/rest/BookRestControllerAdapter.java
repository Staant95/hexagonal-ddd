package com.hexagonal.ddd.demo.adapters.driven.rest;

import com.hexagonal.ddd.demo.adapters.driven.rest.payloads.BookPayload;
import com.hexagonal.ddd.demo.domain.models.Book;
import com.hexagonal.ddd.demo.domain.ports.inbound.BookServicePort;
import com.hexagonal.ddd.demo.domain.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestControllerAdapter {


    private final BookServicePort bookServicePort;

    public BookRestControllerAdapter(BookService bookServicePort) {
        this.bookServicePort = bookServicePort;
    }

    /*
        The request payload entity is NOT, and should not, be an entity from the domain model
        The domain model should remain free from any external dependencies/technologies
    */
    @PostMapping
    public ResponseEntity<BookPayload> addBook(@Valid @RequestBody BookPayload bookPayload) {
        Book book = this.mapToBook(bookPayload);
        Book addedBook = bookServicePort.addBook(book);

        return new ResponseEntity<>(mapToBookPayload(addedBook), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable long id) {
        bookServicePort.removeBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookServicePort.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    // Could be automated with some framework/library
    private Book mapToBook(BookPayload bookPayload) {
        return Book.builder()
                .title(bookPayload.getTitle())
                .author(bookPayload.getAuthor())
                .pages(bookPayload.getPages())
                .stock(bookPayload.getStock())
                .build();
    }

    private BookPayload mapToBookPayload(Book book) {
        return BookPayload.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .pages(book.getPages())
                .stock(book.getStock())
                .build();
    }

}
