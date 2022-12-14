package dev.library.user.domain;

import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@ToString
@DynamicInsert
//TODO: currentRentedBooks 필드 DB INSERT 시 기본값 0으로 적용되도록
//현재 @DynamicInsert, columnDefinition = "int default 0" 적용 안됨
public class UserState {
    @Column(name = "current_rented_books", columnDefinition = "int default 0", nullable = false)
    private int currentRentedBooks;

    @Column(name = "rent_free_date")
    private LocalDate rentFreeDate;

    @Column(name = "rentable", columnDefinition = "varchar(1) default 'Y'", nullable = false) // 대여 가능 여부
    private boolean rentable;

    public UserState() {} // TODO: Q1. 여긴 public, UserId는 protected

    public UserState(int currentRentedBooks, LocalDate rentFreeDate, boolean rentable) {
        this.currentRentedBooks = currentRentedBooks;
        this.rentFreeDate = rentFreeDate;
        this.rentable = rentable;
    }

    public int getCurrentRentedBooks() {
        return currentRentedBooks;
    }

    public LocalDate getRentFreeDate() {
        return rentFreeDate;
    }

    public boolean isRentable() {
        return rentable;
    }

    public boolean checkCurrentRentedBooks(){ return currentRentedBooks >= 2; }

    public boolean checkRentFreeDateIsEmpty(){ return rentFreeDate != null; }

    public boolean rentFreeDateIsBeforeNow(){ return rentFreeDate.isBefore(LocalDate.now()); }

    public boolean rentableIsChangeOrNot(User user){ return user.getUserState().isRentable() != rentable; }
}
