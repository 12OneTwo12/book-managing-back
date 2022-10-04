package dev.library.book.presentation.request;

public class BookRequest {

    String bookId;

    public String getBookId() {
        return bookId;
    }

    public BookRequest() {
    }

    public BookRequest(String bookId) {
        this.bookId = bookId;
    }

}
