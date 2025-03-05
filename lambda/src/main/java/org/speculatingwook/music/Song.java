package org.speculatingwook.music;

public class Song {
    private String title;
    private int duration;        // 노래 길이(초 단위)
    private String genre;
    private double rating;       // 평점(1~5)

    public Song(String title, int duration, String genre, double rating) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.rating = rating;
    }

    // Getters
    public String getTitle() { return title; }
    public int getDuration() { return duration; }
    public String getGenre() { return genre; }
    public double getRating() { return rating; }

    @Override
    public String toString() {
        return "Song{title='" + title + "', duration=" + duration
                + ", genre='" + genre + "', rating=" + rating + "}";
    }
}

