package com.hexagonal.ddd.demo.config;


import com.hexagonal.ddd.demo.adapters.driving.jpa.BookJpaAdapter;
import com.hexagonal.ddd.demo.adapters.driving.jpa.repositories.BookRepository;
import com.hexagonal.ddd.demo.domain.ports.inbound.BookServicePort;
import com.hexagonal.ddd.demo.domain.ports.outbound.BookPersistencePort;
import com.hexagonal.ddd.demo.domain.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {

    @Bean
    public BookServicePort getBookServicePort(BookPersistencePort bookPersistencePort) {
        return new BookService(bookPersistencePort);
    }


    @Bean
    public BookPersistencePort getBookPersistencePort(BookRepository bookRepository) {
        return new BookJpaAdapter(bookRepository);
    }

}
