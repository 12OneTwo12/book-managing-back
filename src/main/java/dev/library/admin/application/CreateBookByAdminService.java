package dev.library.admin.application;

import dev.library.book.dto.BookTitleDTO;

public interface CreateBookByAdminService {

    public String createBook(BookTitleDTO bookTitleDTO);

}
