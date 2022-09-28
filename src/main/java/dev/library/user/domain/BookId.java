package dev.library.user.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@ToString
public class BookId {

    @Column(name = "book_id")
    private String id;

    protected BookId() {}

    public String getId() { return id; }

}
