package com.hexagonal.ddd.demo.adapters.driving.jpa.repositories;

import com.hexagonal.ddd.demo.adapters.driving.jpa.models.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
