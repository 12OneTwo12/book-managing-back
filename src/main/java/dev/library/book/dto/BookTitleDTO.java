package dev.library.book.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookTitleDTO {

    String title;
    public BookTitleDTO() {
    }
    public BookTitleDTO(String title) {
        this.title = title;
    }
}
