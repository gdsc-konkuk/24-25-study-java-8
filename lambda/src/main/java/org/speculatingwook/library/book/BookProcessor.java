package org.speculatingwook.library.book;

import org.speculatingwook.library.Book;

@FunctionalInterface
public interface BookProcessor {
    void process(Book book);
}
