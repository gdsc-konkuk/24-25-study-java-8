package org.speculatingwook.music;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SongService {
    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * 1. 모든 노래의 제목 목록을 반환한다.
     */
    public List<String> getAllSongTitles() {
        return null;
    }

    /**
     * 2. 재생 시간(초) 순으로 노래 목록을 정렬한다.
     */
    public List<Song> getSongsSortedByDuration() {
        return null;
    }

    /**
     * 3. 특정 길이(초) 이상인 노래 목록을 반환한다.
     */
    public List<Song> getSongsOverDuration(int threshold) {
        return null;
    }

    /**
     * 4. 장르별로 노래 목록을 그룹화한다.
     */
    public Map<String, List<Song>> groupSongsByGenre() {
        return null;
    }

    /**
     * 5. 모든 노래의 재생 시간 합을 계산한다.
     */
    public int getTotalDuration() {
        return 0;
    }

    /**
     * 6. 모든 노래의 평균 평점을 계산한다.
     */
    public double getAverageRating() {
        return 0;
    }

    /**
     * 7. 재생 시간 범위 내에 있는 노래 목록을 반환한다.
     */
    public List<Song> getSongsInDurationRange(int minDuration, int maxDuration) {
        return null;
    }

    /**
     * 8. 특정 제목을 가진 노래를 검색한다.
     */
    public Optional<Song> findSongByTitle(String title) {
        return null;
    }

    /**
     * 9. 모든 노래의 재생 시간이 특정 값 이상인지 확인한다.
     */
    public boolean areAllSongsAboveDuration(int threshold) {
        return false;
    }

    /**
     * 10. 주어진 조건에 일치하는 노래를 검색한다.
     */
    public Optional<Song> findSong(Predicate<Song> predicate) {
        return null;
    }

    /**
     * 11. 장르별로 가장 긴 재생 시간을 가진 노래를 찾는다.
     */
    public Map<String, Song> getLongestSongByGenre() {
        return null;
    }

    /**
     * 12. 제목의 길이가 가장 긴 노래를 찾는다.
     */
    public Optional<Song> getSongWithLongestTitle() {
        return null;
    }

    /**
     * 13. 특정 길이(초) 이상인 노래의 제목을 대문자로 변환한다.
     */
    public List<String> getUpperCaseTitlesOfSongsAboveDuration(int threshold) {
        return null;
    }

    /**
     * 14. 노래 목록을 주어진 매퍼 함수를 통해 변환한다.
     */
    public <R> List<R> mapSongs(Function<Song, R> mapper) {
        return null;
    }

    /**
     * 15. 모든 노래 제목을 연결하여 하나의 문자열로 만든다.
     */
    public String getAllSongTitlesToString() {
        return null;
    }

    /**
     * 16-1. 장르별 평균 재생 시간을 계산한다.
     */
    public Map<String, Double> getAverageDurationByGenre() {
        return null;
    }

    /**
     * 16-2. 장르별 평균 재생 시간을 기준으로 장르를 내림차순으로 정렬한다.
     */
    public List<Map.Entry<String, Double>> getGenresSortedByAverageDuration() {
        Map<String, Double> avgDurationMap = getAverageDurationByGenre();
        return null;
    }

    /**
     * 17. 주어진 조건에 맞는 노래 목록을 필터링한다.
     */
    public List<Song> filterSongs_1(Predicate<Song> predicate) {
        return null;
    }

    /**
     * 18. 주어진 조건에 맞는 노래 목록을 필터링한다.
     */
    public List<Song> filterSongs_2(Predicate<Song> predicate) {
        return null;
    }

    /**
     * 19. 주어진 작업을 각 노래에 수행한다.
     */
    public void processSongs(Consumer<Song> consumer) {
        songs.forEach(consumer);
    }

    /**
     * 20. 노래 목록을 주어진 비교 기준에 따라 정렬한다.
     */
    public void sortSongs(Comparator<Song> comparator) {
        songs.sort(comparator);
    }

    /**
     * 21. 모든 노래의 평균 재생 시간을 계산한다.
     */
    public double getAverageDuration() {
        return 0;
    }

    /**
     * 모든 노래 목록을 반환한다.
     */
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }
}

