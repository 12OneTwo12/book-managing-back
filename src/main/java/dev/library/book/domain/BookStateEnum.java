package dev.library.book.domain;

public enum BookStateEnum {

    ABLE("Available"),
    UNABLE("Unavailable");

    private String value;

    private BookStateEnum(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
