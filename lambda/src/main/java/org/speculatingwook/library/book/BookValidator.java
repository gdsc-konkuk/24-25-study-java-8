package org.speculatingwook.library.book;

import org.speculatingwook.library.Book;

@FunctionalInterface
public interface BookValidator {
    boolean validate(Book book);
}
