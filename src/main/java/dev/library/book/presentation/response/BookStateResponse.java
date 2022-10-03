package dev.library.book.presentation.response;

public class BookStateResponse {
    String bookId;
    String title;
    String bookState;

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getBookState() {
        return bookState;
    }
    public BookStateResponse(){

    }

    public BookStateResponse(String bookId, String title, String bookState) {
        this.bookId = bookId;
        this.title = title;
        this.bookState = bookState;
    }

}
