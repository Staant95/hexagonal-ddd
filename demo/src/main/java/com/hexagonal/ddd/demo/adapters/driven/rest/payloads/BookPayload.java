package com.hexagonal.ddd.demo.adapters.driven.rest.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookPayload {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @Min(50)
    private int pages;

    @Min(0)
    private int stock;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookPayload that = (BookPayload) o;

        return new EqualsBuilder()
                .append(pages, that.pages)
                .append(stock, that.stock)
                .append(title, that.title)
                .append(author, that.author)
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
