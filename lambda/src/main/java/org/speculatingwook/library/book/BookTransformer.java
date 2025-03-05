package org.speculatingwook.library.book;

import org.speculatingwook.library.Book;

@FunctionalInterface
public interface BookTransformer <T>{
    T transform(Book book);
}
