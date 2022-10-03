package dev.library.book.application;

import dev.library.book.domain.BookStateEnum;
import dev.library.book.presentation.request.BookRequest;

public interface BookStateService {

    BookStateEnum changeBookState(BookRequest bookRequest);

}
