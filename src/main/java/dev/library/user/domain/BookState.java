package dev.library.user.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@ToString
public class BookState {

    @Column(name = "able_or_not") // 대여 가능 여부
    public boolean ableOrNot;

    public BookState(boolean ableOrNot) {}
}
