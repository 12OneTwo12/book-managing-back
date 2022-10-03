package dev.library.book.domain;


import lombok.AccessLevel;

import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@ToString
@Setter(AccessLevel.PROTECTED)
public class BookState {

    @Column(name = "book_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStateEnum bookStateEnum;

    protected BookState() {}

    public BookStateEnum getBookStateEnum() {
        return bookStateEnum;
    }

    public BookState(BookStateEnum bookStateEnum) {
        this.bookStateEnum = bookStateEnum;
    }
}
