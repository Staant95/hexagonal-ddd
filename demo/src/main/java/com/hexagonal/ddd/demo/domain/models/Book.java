package com.hexagonal.ddd.demo.domain.models;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/*
    Lombok should not be used, the domain model should not have any
    external dependencies!
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private String title;

    private String author;

    private int pages;

    private int stock;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;

        return new EqualsBuilder()
                .append(pages, book.pages)
                .append(stock, book.stock)
                .append(title, book.title)
                .append(author, book.author)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(author)
                .append(pages)
                .append(stock)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("author", author)
                .append("pages", pages)
                .append("stock", stock)
                .toString();
    }
}
