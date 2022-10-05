package dev.library.rental.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAndBookIdDTO {
    private String userId;
    private String bookId;
}
