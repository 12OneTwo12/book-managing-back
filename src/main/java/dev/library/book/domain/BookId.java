package dev.library.book.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString
public class BookId implements Serializable {

    @Column(name = "book_id")
    private String id;

    protected BookId() {}

    public String getId() { return id; }

    public BookId(String id) {
        this.id = id;
    }

    public static BookId of(String id) {return new BookId(id);}
}
