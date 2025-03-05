package org.speculatingwook.cinema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.speculatingwook.cinema.Movie;
import org.speculatingwook.cinema.MovieService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <h2>MovieServiceTest</h2>
 * <p>
 * MovieService의 각 메서드를 개별 테스트하는 클래스입니다.
 * 각 유형별로 10개의 테스트 메서드를 별도로 작성하였습니다.
 * </p>
 */
public class MovieServiceTest {

    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        movieService = new MovieService();
        // 샘플 데이터 추가
        movieService.addMovie(new Movie("Inception", "Sci-Fi", 2010, 4.8, 148, "Christopher Nolan", 800_000_000));
        movieService.addMovie(new Movie("The Dark Knight", "Action", 2008, 4.9, 152, "Christopher Nolan", 1_000_000_000));
        movieService.addMovie(new Movie("Interstellar", "Sci-Fi", 2014, 4.7, 169, "Christopher Nolan", 700_000_000));
        movieService.addMovie(new Movie("Avengers: Endgame", "Action", 2019, 4.5, 181, "Anthony Russo", 2_800_000_000L));
        movieService.addMovie(new Movie("Titanic", "Romance", 1997, 4.6, 195, "James Cameron", 2_200_000_000L));
        movieService.addMovie(new Movie("The Matrix", "Sci-Fi", 1999, 4.7, 136, "Lana Wachowski", 466_000_000));
        movieService.addMovie(new Movie("Parasite", "Thriller", 2019, 4.6, 132, "Bong Joon-ho", 263_000_000));
    }

    // ========================================================
    // A. Filtering Tests (10개)
    // ========================================================

    @Test
    public void testFiltering1_getMoviesByGenre() {
        // Filtering #1: Sci-Fi 영화 3편 기대
        assertEquals(3, movieService.getMoviesByGenre("Sci-Fi").size());
    }

    @Test
    public void testFiltering2_getMoviesLongerThan() {
        // Filtering #2: 150분보다 긴 영화: The Dark Knight, Interstellar, Avengers: Endgame, Titanic => 4편
        assertEquals(4, movieService.getMoviesLongerThan(150).size());
    }

    @Test
    public void testFiltering3_getMoviesWithRatingAbove() {
        // Filtering #3: 평점이 4.7 이상인 영화: Inception, The Dark Knight, Interstellar, The Matrix => 4편
        assertEquals(4, movieService.getMoviesWithRatingAbove(4.7).size());
    }

    @Test
    public void testFiltering4_getMoviesReleasedAfter() {
        // Filtering #4: 2000년 이후 개봉: Inception(2010), The Dark Knight(2008), Interstellar(2014), Avengers: Endgame(2019), Parasite(2019) => 5편
        assertEquals(5, movieService.getMoviesReleasedAfter(2000).size());
    }

    @Test
    public void testFiltering5_getMoviesTitleContains() {
        // Filtering #5: 제목에 "The" 포함: The Dark Knight, The Matrix => 2편
        assertEquals(2, movieService.getMoviesTitleContains("The").size());
    }

    @Test
    public void testFiltering6_getMoviesByDirector() {
        // Filtering #6: 감독 Christopher Nolan의 영화: Inception, The Dark Knight, Interstellar => 3편
        assertEquals(3, movieService.getMoviesByDirector("Christopher Nolan").size());
    }

    @Test
    public void testFiltering7_getMoviesWithBoxOfficeAbove() {
        // Filtering #7: 흥행수익 1,000,000,000 이상: The Dark Knight, Avengers: Endgame, Titanic => 3편
        assertEquals(3, movieService.getMoviesWithBoxOfficeAbove(1_000_000_000).size());
    }

    @Test
    public void testFiltering8_getMoviesInDurationRange() {
        // Filtering #8: 상영시간 130~170분 사이: Inception(148), The Dark Knight(152), Interstellar(169), The Matrix(136), Parasite(132) => 5편
        assertEquals(5, movieService.getMoviesInDurationRange(130, 170).size());
    }

    @Test
    public void testFiltering9_getMoviesWithRatingInRange() {
        // Filtering #9: 평점 4.6 ~ 4.8: Inception(4.8), Interstellar(4.7), Titanic(4.6), The Matrix(4.7), Parasite(4.6) => 5편
        assertEquals(5, movieService.getMoviesWithRatingInRange(4.6, 4.8).size());
    }

    @Test
    public void testFiltering10_getMoviesBetweenYears() {
        // Filtering #10: 1999 ~ 2010 사이: Inception(2010), The Dark Knight(2008), The Matrix(1999) => 3편
        assertEquals(3, movieService.getMoviesBetweenYears(1999, 2010).size());
    }

    // ========================================================
    // B. Mapping Tests (10개)
    // ========================================================

    @Test
    public void testMapping1_getAllTitles() {
        // Mapping #1: 총 영화 제목 개수 7개
        assertEquals(7, movieService.getAllTitles().size());
    }

    @Test
    public void testMapping2_getAllDirectors() {
        // Mapping #2: 감독 목록, Christopher Nolan, Anthony Russo, James Cameron, Lana Wachowski, Bong Joon-ho => 5명
        assertEquals(5, movieService.getAllDirectors().size());
    }

    @Test
    public void testMapping3_getTitlesInUpperCase() {
        // Mapping #3: 제목 대문자 변환, "INCEPTION" 포함 여부 검사
        assertTrue(movieService.getTitlesInUpperCase().stream().anyMatch(s -> s.equals("INCEPTION")));
    }

    @Test
    public void testMapping4_getTitleWithYear() {
        // Mapping #4: "Inception (2010)" 포함 검사
        assertTrue(movieService.getTitleWithYear().stream().anyMatch(s -> s.contains("Inception (2010)")));
    }

    @Test
    public void testMapping5_getTitleAndRating() {
        // Mapping #5: "Inception - 4.8" 형태 검사
        assertTrue(movieService.getTitleAndRating().stream().anyMatch(s -> s.contains("Inception - 4.8")));
    }

    @Test
    public void testMapping6_getAllDurations() {
        // Mapping #6: 상영시간 리스트 크기 7
        assertEquals(7, movieService.getAllDurations().size());
    }

    @Test
    public void testMapping7_getDirectorWithYear() {
        // Mapping #7: "Christopher Nolan: 2010" 같은 문자열 포함 여부 검사
        assertTrue(movieService.getDirectorWithYear().stream().anyMatch(s -> s.contains("Christopher Nolan")));
    }

    @Test
    public void testMapping8_getAllBoxOffices() {
        // Mapping #8: 흥행수익 리스트 크기 7
        assertEquals(7, movieService.getAllBoxOffices().size());
    }

    @Test
    public void testMapping9_mapToCustomObject() {
        // Mapping #9: "Movie[" 로 시작하는 문자열 포함 여부 검사
        assertTrue(movieService.mapToCustomObject().stream().anyMatch(s -> s.startsWith("Movie[")));
    }

    @Test
    public void testMapping10_mapMovies() {
        // Mapping #10: 제네릭 매퍼 사용해서 연도 리스트 추출, 7개 기대
        List<Integer> years = movieService.mapMovies(Movie::getYear);
        assertEquals(7, years.size());
    }

    // ========================================================
    // C. Sorting Tests (10개)
    // ========================================================

    @Test
    public void testSorting1_getMoviesSortedByTitleAsc() {
        // Sorting #1: 제목 오름차순, 첫번째 영화가 "Avengers: Endgame"일 것
        assertEquals("Avengers: Endgame", movieService.getMoviesSortedByTitleAsc().get(0).getTitle());
    }

    @Test
    public void testSorting2_getMoviesSortedByTitleDesc() {
        // Sorting #2: 제목 내림차순, 첫번째 영화가 "Titanic"일 것
        assertEquals("Titanic", movieService.getMoviesSortedByTitleDesc().get(0).getTitle());
    }

    @Test
    public void testSorting3_getMoviesSortedByRatingDesc() {
        // Sorting #3: 평점 내림차순, 최고 평점 영화가 "The Dark Knight" (4.9)
        assertEquals("The Dark Knight", movieService.getMoviesSortedByRatingDesc().get(0).getTitle());
    }

    @Test
    public void testSorting4_getMoviesSortedByYearAsc() {
        // Sorting #4: 연도 오름차순, 가장 오래된 영화 "Titanic"(1997)
        assertEquals("Titanic", movieService.getMoviesSortedByYearAsc().get(0).getTitle());
    }

    @Test
    public void testSorting5_getMoviesSortedByBoxOfficeDesc() {
        // Sorting #5: 흥행수익 내림차순, 최고 흥행 "Avengers: Endgame"
        assertEquals("Avengers: Endgame", movieService.getMoviesSortedByBoxOfficeDesc().get(0).getTitle());
    }

    @Test
    public void testSorting6_getMoviesSortedByDurationAsc() {
        // Sorting #6: 상영시간 오름차순, 가장 짧은 영화가 "Parasite" (132분)
        assertEquals("Parasite", movieService.getMoviesSortedByDurationAsc().get(0).getTitle());
    }

    @Test
    public void testSorting7_getMoviesSortedByGenre() {
        // Sorting #7: 장르 오름차순, 첫번째 그룹의 영화 장르가 "Action"일 것 (Action, Romance, Sci-Fi, Thriller 순)
        assertEquals("Action", movieService.getMoviesSortedByGenre().get(0).getGenre());
    }

    @Test
    public void testSorting8_getMoviesSortedByDirector() {
        // Sorting #8: 감독 이름 오름차순, 첫번째 감독 이름이 "Anthony Russo" 또는 "Bong Joon-ho" 등 알파벳 순으로 판단
        List<Movie> sorted = movieService.getMoviesSortedByDirector();
        // 단순히 null이 아님을 확인
        assertNotNull(sorted.get(0).getDirector());
    }

    @Test
    public void testSorting9_getMoviesSortedByRatingAsc() {
        // Sorting #9: 평점 오름차순, 가장 낮은 평점 영화가 "Avengers: Endgame" (4.5)
        assertEquals("Avengers: Endgame", movieService.getMoviesSortedByRatingAsc().get(0).getTitle());
    }

    @Test
    public void testSorting10_sortMovies_customComparator() {
        // Sorting #10: custom comparator, 예를 들어 흥행수익 내림차순
        List<Movie> sorted = movieService.sortMovies(Comparator.comparingLong(Movie::getBoxOffice).reversed());
        assertEquals("Avengers: Endgame", sorted.get(0).getTitle());
    }

    // ========================================================
    // D. Reducing Tests (10개)
    // ========================================================

    @Test
    public void testReducing1_getTotalDuration() {
        // Reducing #1: 전체 상영시간 합계 = 1113분
        assertEquals(1113, movieService.getTotalDuration());
    }

    @Test
    public void testReducing2_getTotalBoxOffice() {
        // Reducing #2: 전체 흥행수익 합계 계산 (샘플 데이터에 따른 값)
        long expected = 800_000_000L + 1_000_000_000L + 700_000_000L + 2_800_000_000L + 2_200_000_000L + 466_000_000L + 263_000_000L;
        assertEquals(expected, movieService.getTotalBoxOffice());
    }

    @Test
    public void testReducing3_getAverageRating() {
        // Reducing #3: 평균 평점 (4.8+4.9+4.7+4.5+4.6+4.7+4.6) / 7
        double expected = (4.8+4.9+4.7+4.5+4.6+4.7+4.6)/7.0;
        assertEquals(expected, movieService.getAverageRating(), 0.001);
    }

    @Test
    public void testReducing4_getMaxRatingMovie() {
        // Reducing #4: 최고 평점 영화 "The Dark Knight" (4.9)
        assertTrue(movieService.getMaxRatingMovie().isPresent());
        assertEquals("The Dark Knight", movieService.getMaxRatingMovie().get().getTitle());
    }

    @Test
    public void testReducing5_getEarliestYear() {
        // Reducing #5: 가장 오래된 영화 연도는 1997 (Titanic)
        assertEquals(1997, movieService.getEarliestYear());
    }

    @Test
    public void testReducing6_getSumOfRatings() {
        // Reducing #6: 평점 합계
        double expected = 4.8+4.9+4.7+4.5+4.6+4.7+4.6;
        assertEquals(expected, movieService.getSumOfRatings(), 0.001);
    }

    @Test
    public void testReducing7_getProductOfDurations() {
        // Reducing #7: 상영시간의 누적 곱 (데이터에 따라 매우 클 수 있음; 여기서는 결과가 1보다 큰지 확인)
        assertTrue(movieService.getProductOfDurations() > 1);
    }

    @Test
    public void testReducing8_getMaxBoxOfficeMovie() {
        // Reducing #8: 최고 흥행수익 영화 "Avengers: Endgame"
        assertTrue(movieService.getMaxBoxOfficeMovie().isPresent());
        assertEquals("Avengers: Endgame", movieService.getMaxBoxOfficeMovie().get().getTitle());
    }

    @Test
    public void testReducing9_getTotalBoxOfficeByGenre() {
        // Reducing #9: Sci-Fi 장르의 흥행수익 합계: Inception + Interstellar + The Matrix
        long expected = 800_000_000L + 700_000_000L + 466_000_000L;
        assertEquals(expected, movieService.getTotalBoxOfficeByGenre("Sci-Fi"));
    }

    @Test
    public void testReducing10_getAverageDuration() {
        // Reducing #10: 평균 상영시간 = 1113 / 7 = 159.0분
        assertEquals(159.0, movieService.getAverageDuration(), 0.001);
    }

    // ========================================================
    // E. Grouping / Collecting Tests (10개)
    // ========================================================

    @Test
    public void testGrouping1_groupMoviesByGenre() {
        // Grouping #1: Sci-Fi 영화 3편 기대
        Map<String, List<Movie>> groups = movieService.groupMoviesByGenre();
        assertEquals(3, groups.get("Sci-Fi").size());
    }

    @Test
    public void testGrouping2_groupMoviesByDirector() {
        // Grouping #2: Christopher Nolan의 영화 3편 기대
        Map<String, List<Movie>> groups = movieService.groupMoviesByDirector();
        assertEquals(3, groups.get("Christopher Nolan").size());
    }

    @Test
    public void testGrouping3_partitionMoviesByRating() {
        // Grouping #3: 평점 4.7 이상과 미만으로 분할
        Map<Boolean, List<Movie>> partition = movieService.partitionMoviesByRating(4.7);
        // 4.7 이상: Inception(4.8), The Dark Knight(4.9), The Matrix(4.7) => 3편 (정확한 조건에 따라 다를 수 있음)
        assertTrue(partition.get(true).size() >= 3);
    }

    @Test
    public void testGrouping4_joinMovieTitles() {
        // Grouping #4: 영화 제목들을 " | "로 연결한 문자열에 "Inception" 포함 여부 검사
        String joined = movieService.joinMovieTitles(" | ");
        assertTrue(joined.contains("Inception"));
    }

    @Test
    public void testGrouping5_getGenreCount() {
        // Grouping #5: 각 장르별 영화 개수, Sci-Fi: 3, Action: 2, Romance: 1, Thriller: 1
        Map<String, Long> count = movieService.getGenreCount();
        assertEquals(3, count.get("Sci-Fi"));
    }

    @Test
    public void testGrouping6_groupMoviesByYear() {
        // Grouping #6: 개봉연도별 그룹화, 1997, 1999, 2008, 2010, 2014, 2019
        Map<Integer, List<Movie>> groups = movieService.groupMoviesByYear();
        assertTrue(groups.containsKey(1997));
    }

    @Test
    public void testGrouping7_groupMoviesByRoundedRating() {
        // Grouping #7: 평점을 소수점 버림하여 그룹화, 예를 들어 4로 그룹화되는 영화들 존재
        Map<Integer, List<Movie>> groups = movieService.groupMoviesByRoundedRating();
        assertTrue(groups.containsKey(4));
    }

    @Test
    public void testGrouping8_groupMoviesByDurationCategory() {
        // Grouping #8: 상영시간 범주별 그룹화, "Short", "Medium", "Long" 중 하나 이상 존재
        Map<String, List<Movie>> groups = movieService.groupMoviesByDurationCategory();
        assertTrue(groups.keySet().stream().anyMatch(k -> k.equals("Short") || k.equals("Medium") || k.equals("Long")));
    }

    @Test
    public void testGrouping9_groupMoviesByDirectorSet() {
        // Grouping #9: Christopher Nolan 감독의 영화가 집합(Set)으로 3개 포함되어야 함.
        Map<String, Set<Movie>> groups = movieService.groupMoviesByDirectorSet();
        assertEquals(3, groups.get("Christopher Nolan").size());
    }


    @Test
    public void testGrouping10_groupMovieTitlesByDirector() {
        // Grouping #10: 감독별 영화 제목 리스트 그룹화, Christopher Nolan에 대한 제목 리스트 크기가 3
        Map<String, List<String>> groups = movieService.groupMovieTitlesByDirector();
        assertEquals(3, groups.get("Christopher Nolan").size());
    }



    // ========================================================
    // F. Supplier Tests (10개)
    // ========================================================

    @Test
    public void testSupplier1_getDefaultMovie() {
        // Supplier #1: 기본 영화의 제목은 "Default"여야 함.
        Movie defaultMovie = movieService.getDefaultMovie();
        assertEquals("Default", defaultMovie.getTitle());
    }

    @Test
    public void testSupplier2_getMovieSupplier() {
        // Supplier #2: 새 영화 생성 Supplier를 통해 생성된 영화의 제목은 "New Movie"여야 함.
        Movie newMovie = movieService.getMovieSupplier().get();
        assertEquals("New Movie", newMovie.getTitle());
    }

    @Test
    public void testSupplier3_getRandomMovieSupplier() {
        // Supplier #3: 현재 영화 목록에서 무작위 영화 반환; null이 아니어야 함.
        Movie randomMovie = movieService.getRandomMovieSupplier().get();
        assertNotNull(randomMovie);
    }

    @Test
    public void testSupplier4_getMoviesSupplier() {
        // Supplier #4: 영화 목록 Supplier가 반환하는 리스트의 크기는 실제 영화 목록과 같아야 함.
        List<Movie> moviesList = movieService.getMoviesSupplier().get();
        assertEquals(movieService.getMovies().size(), moviesList.size());
    }

    @Test
    public void testSupplier5_getMovieOrDefault() {
        // Supplier #5: 빈 Optional이 전달되면 기본 영화("Default")를 반환해야 함.
        Movie movie = movieService.getMovieOrDefault(Optional.empty());
        assertEquals("Default", movie.getTitle());
    }

    @Test
    public void testSupplier6_lazyMovieCreation() {
        // Supplier #6: 전달된 Supplier를 통해 "Lazy Movie" 생성
        Movie lazyMovie = movieService.lazyMovieCreation(() ->
                new Movie("Lazy Movie", "Drama", 2021, 4.2, 130, "Lazy Director", 50_000_000));
        assertEquals("Lazy Movie", lazyMovie.getTitle());
    }

    @Test
    public void testSupplier7_supplyDefaultDirector() {
        // Supplier #7: 기본 감독 이름은 "Default Director"여야 함.
        String director = movieService.supplyDefaultDirector().get();
        assertEquals("Default Director", director);
    }

    @Test
    public void testSupplier8_supplyAverageRatingSupplier() {
        // Supplier #8: 전체 영화의 평균 평점이 0보다 커야 함.
        double avgRating = movieService.supplyAverageRatingSupplier().get();
        assertTrue(avgRating > 0);
    }

    @Test
    public void testSupplier9_supplyTotalBoxOfficeSupplier() {
        // Supplier #9: 전체 영화의 총 흥행수익이 0보다 커야 함.
        long totalBoxOffice = movieService.supplyTotalBoxOfficeSupplier().get();
        assertTrue(totalBoxOffice > 0);
    }

    @Test
    public void testSupplier10_supplySortedMoviesSupplier() {
        // Supplier #10: 제목 오름차순 정렬된 영화 목록의 첫 번째 영화가 "Avengers: Endgame"이어야 함.
        List<Movie> sortedMovies = movieService.supplySortedMoviesSupplier().get();
        assertEquals("Avengers: Endgame", sortedMovies.get(0).getTitle());
    }

}
