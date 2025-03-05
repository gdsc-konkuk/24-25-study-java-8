package org.speculatingwook.cinema;

/**
 * <h3>Movie 도메인 클래스</h3>
 * <p>
 * 영화 정보를 담는 클래스입니다.
 * </p>
 * 속성:
 * - title: 영화 제목
 * - genre: 영화 장르
 * - year: 개봉 연도
 * - rating: 평점 (0.0 ~ 5.0)
 * - duration: 상영 시간(분)
 * - director: 감독 이름
 * - boxOffice: 흥행 수익 (USD)
 */
public class Movie {
    private String title;
    private String genre;
    private int year;
    private double rating;
    private int duration;
    private String director;
    private long boxOffice;

    public Movie(String title, String genre, int year, double rating, int duration, String director, long boxOffice) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.duration = duration;
        this.director = director;
        this.boxOffice = boxOffice;
    }

    // Getter 메서드들
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
    public double getRating() { return rating; }
    public int getDuration() { return duration; }
    public String getDirector() { return director; }
    public long getBoxOffice() { return boxOffice; }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", duration=" + duration +
                ", director='" + director + '\'' +
                ", boxOffice=" + boxOffice +
                '}';
    }
}
