package dev.library.rental.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BookIdsDTO {
    private List<String> bookId;
}
