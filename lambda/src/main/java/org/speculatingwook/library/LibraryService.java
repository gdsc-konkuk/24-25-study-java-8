package org.speculatingwook.library;

import org.speculatingwook.library.book.BookProcessor;
import org.speculatingwook.library.book.BookTransformer;
import org.speculatingwook.library.book.BookValidator;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class LibraryService {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * 1. 주어진 조건에 맞는 책들을 찾습니다.
     * @param predicate 조건을 검사할 Predicate
     * @return 조건에 맞는 책들의 리스트
     */
    public List<Book> findBooks(Predicate<Book> predicate) {
        return books.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /**
     * 2. 저자별로 책을 그룹화합니다.
     * @return 저자별 책 리스트 맵
     */
    public Map<String, List<Book>> groupBooksByAuthor() {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
    }

    /**
     * 3. 책의 카테고리별로 책의 개수를 셉니다.
     * flatMap과 groupingBy를 사용합니다.
     * @return 카테고리별 책의 개수
     */
    public Map<String, Long> countBooksByCategory() {
        return books.stream()
                .flatMap(book -> book.getCategories().stream())
                .collect(Collectors.groupingBy(category -> category, Collectors.counting()));
    }

    /**
     * 4. 가장 인기 있는 카테고리를 찾습니다.
     * limit, sorted를 사용합니다.
     * @param n 상위 n개의 인기 카테고리
     * @return 인기 카테고리 리스트
     */
    public List<String> getMostPopularCategories(int n) {
        return books.stream()
                .flatMap(book -> book.getCategories().stream())
                .collect(Collectors.groupingBy(category -> category, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .limit(n)
                .collect(Collectors.toList());
    }

    /**
     * 5. 책들의 평균 나이를 구합니다.
     * mapToLong을 사용합니다.
     * @return 평균 책 나이
     */
    public double getAverageBookAge() {
        return books.stream()
                .mapToLong(book -> LocalDate.now().getYear() - book.getPublishDate().getYear())
                .average()
                .orElse(0);
    }

    /**
     * 6. 최근에 출판된 책들을 가져옵니다.
     * sorted와 limit을 사용합니다.
     * @param n 가져올 책의 수
     * @return 최근 책들의 리스트
     */
    public List<Book> getRecentBooks(int n) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getPublishDate).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    /**
     * 7 - 1. 책을 대출합니다.
     * @param isbn 대출할 책의 ISBN 번호
     * @return 대출 성공 여부
     */
    public boolean lendBook(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn) && book.isAvailable())
                .findFirst()
                .map(book -> {
                    book.setAvailable(false);
                    return true;
                })
                .orElse(false);
    }

    /**
     * 7 - 2. 책을 반납합니다.
     * @param isbn 반납할 책의 ISBN 번호
     */
    public void returnBook(String isbn) {
        books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .ifPresent(book -> book.setAvailable(true));
    }

    /**
     * 8. 책을 대출 가능 여부에 따라 구분합니다.
     * @return 대출 가능 여부에 따른 책들의 맵
     */
    public Map<Boolean, List<Book>> partitionBooksByAvailability() {
        return books.stream()
                .collect(Collectors.partitioningBy(Book::isAvailable));
    }

    /**
     * 9. 가장 많은 책을 출판한 저자를 찾습니다.
     * entrySet, max, map을 사용합니다.
     * @return 가장 많은 책을 출판한 저자
     */
    public String getMostProlificAuthor() {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * 10. 책 제목들의 총 길이를 계산합니다.
     * reducing을 사용합니다.
     * @return 책 제목의 총 길이
     */
    public int getTotalTitleLength() {
        return books.stream()
                .mapToInt(book -> book.getTitle().length())
                .sum();
    }

    /**
     * 11. 커스텀 BookProcessor를 사용하여 책을 처리합니다.
     * @param processor 책을 처리할 프로세서
     */
    public void processBooks(BookProcessor processor) {
        books.forEach(processor::process);
    }

    /**
     * 12. BookValidator를 사용하여 책을 검증합니다.
     * @param validator 책을 검증할 Validator
     * @return 검증된 책 리스트
     */
    public List<Book> getValidBooks(BookValidator validator) {
        return books.stream()
                .filter(validator::validate)
                .collect(Collectors.toList());
    }

    /**
     * 13. BookTransformer를 사용하여 책을 변환합니다.
     * @param transformer 책을 변환할 Transformer
     * @param <T> 변환된 타입
     * @return 변환된 결과 리스트
     */
    public <T> List<T> transformBooks(BookTransformer<T> transformer) {
        return books.stream()
                .map(transformer::transform)
                .collect(Collectors.toList());
    }

    /**
     * 14. Supplier를 사용하여 새 책을 생성합니다.
     * @param bookSupplier 새 책을 생성할 Supplier
     */
    public void addNewBook(Supplier<Book> bookSupplier) {
        Book newBook = bookSupplier.get();
        addBook(newBook);
    }

    /**
     * 15 - 1. ISBN을 기준으로 책을 찾습니다.
     * @param isbn ISBN 번호
     * @return 찾은 책 (Optional로 감싸진 값)
     */
    public Optional<Book> findBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    /**
     * 15 - 2. BiFunction을 사용하여 두 개의 책을 비교합니다.
     * @param book1 첫 번째 책
     * @param book2 두 번째 책
     * @param comparator 책을 비교할 Comparator
     * @param <T> 비교 결과 타입
     * @return 비교 결과
     */
    public <T> T compareBooks(Book book1, Book book2, BiFunction<Book, Book, T> comparator) {
        return comparator.apply(book1, book2);
    }

    /**
     * 16 - 1. UnaryOperator를 사용하여 책의 상태를 업데이트합니다.
     * @param isbn 업데이트할 책의 ISBN 번호
     * @param updater 책을 업데이트할 UnaryOperator
     */
    public void updateBookState(String isbn, UnaryOperator<Book> updater) {
        findBookByIsbn(isbn).ifPresent(book -> {
            Book updatedBook = updater.apply(book);
            books.remove(book);
            books.add(updatedBook);
        });
    }
}
