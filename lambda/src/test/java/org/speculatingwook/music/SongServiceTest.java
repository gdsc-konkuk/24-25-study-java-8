package org.speculatingwook.music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.speculatingwook.music.Song;
import org.speculatingwook.music.SongService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SongServiceTest {

    private SongService songService;

    @BeforeEach
    public void setUp() {
        songService = new SongService();
        songService.addSong(new Song("Bohemian Rhapsody", 354, "Rock", 4.9));
        songService.addSong(new Song("Imagine", 183, "Pop", 4.8));
        songService.addSong(new Song("Hotel California", 391, "Rock", 4.7));
        songService.addSong(new Song("Stairway to Heaven", 482, "Rock", 4.9));
        songService.addSong(new Song("Shape of You", 233, "Pop", 4.5));
    }

    // 1.
    @Test
    public void testGetAllSongTitles() {
        List<String> titles = songService.getAllSongTitles();
        assertEquals(5, titles.size());
        assertTrue(titles.containsAll(Arrays.asList(
                "Bohemian Rhapsody", "Imagine",
                "Hotel California", "Stairway to Heaven", "Shape of You"
        )));
    }

    // 2.
    @Test
    public void testGetSongsSortedByDuration() {
        List<Song> sorted = songService.getSongsSortedByDuration();
        // 가장 짧은: Imagine(183), 가장 긴: Stairway to Heaven(482)
        assertEquals("Imagine", sorted.get(0).getTitle());
        assertEquals("Stairway to Heaven", sorted.get(sorted.size() - 1).getTitle());
    }

    // 3.
    @Test
    public void testGetSongsOverDuration() {
        List<Song> longSongs = songService.getSongsOverDuration(300);
        // 300초 이상인 곡: Bohemian Rhapsody(354), Hotel California(391), Stairway to Heaven(482)
        assertEquals(3, longSongs.size());
        assertTrue(longSongs.stream().allMatch(song -> song.getDuration() >= 300));
    }

    // 4.
    @Test
    public void testGroupSongsByGenre() {
        Map<String, List<Song>> grouped = songService.groupSongsByGenre();
        // Rock(3곡), Pop(2곡)
        assertEquals(2, grouped.size());
        assertEquals(3, grouped.get("Rock").size());
        assertEquals(2, grouped.get("Pop").size());
    }

    // 5.
    @Test
    public void testGetTotalDuration() {
        int total = songService.getTotalDuration();
        // 354 + 183 + 391 + 482 + 233 = 1643
        assertEquals(1643, total);
    }

    // 6.
    @Test
    public void testGetAverageRating() {
        double avg = songService.getAverageRating();
        // (4.9 + 4.8 + 4.7 + 4.9 + 4.5) / 5 = 4.76
        assertEquals(4.76, avg, 0.01);
    }

    // 7.
    @Test
    public void testGetSongsInDurationRange() {
        List<Song> inRange = songService.getSongsInDurationRange(200, 400);
        // 200~400초 사이: Bohemian Rhapsody(354), Hotel California(391), Shape of You(233)
        assertEquals(3, inRange.size());
        assertTrue(inRange.stream().allMatch(s -> s.getDuration() >= 200 && s.getDuration() <= 400));
    }

    // 8.
    @Test
    public void testFindSongByTitle() {
        Optional<Song> song = songService.findSongByTitle("Hotel California");
        assertTrue(song.isPresent());
        assertEquals(391, song.get().getDuration());
    }

    // 9.
    @Test
    public void testAreAllSongsAboveDuration() {
        // 모든 곡이 180초 이상인가?
        assertTrue(songService.areAllSongsAboveDuration(180));
        // 모든 곡이 200초 이상인가?
        assertFalse(songService.areAllSongsAboveDuration(200));
        // (Imagine이 183초이므로 200 이상 아님)
    }

    // 10.
    @Test
    public void testFindSong() {
        // Rock 장르 중 하나를 찾기
        Optional<Song> rockSong = songService.findSong(s -> s.getGenre().equals("Rock"));
        assertTrue(rockSong.isPresent());
        // 세 곡 중 아무거나 올 수 있으니 제목만 확인
        assertTrue(Arrays.asList("Bohemian Rhapsody", "Hotel California", "Stairway to Heaven")
                .contains(rockSong.get().getTitle()));
    }

    // 11.
    @Test
    public void testGetLongestSongByGenre() {
        Map<String, Song> longestByGenre = songService.getLongestSongByGenre();
        // Rock 중 가장 긴 곡: Stairway to Heaven(482)
        assertEquals("Stairway to Heaven", longestByGenre.get("Rock").getTitle());
        // Pop 중 가장 긴 곡: Shape of You(233) vs Imagine(183) -> Shape of You
        assertEquals("Shape of You", longestByGenre.get("Pop").getTitle());
    }

    // 12.
    @Test
    public void testGetSongWithLongestTitle() {
        Optional<Song> longestTitle = songService.getSongWithLongestTitle();
        assertTrue(longestTitle.isPresent());
        // "Stairway to Heaven"(18자), "Bohemian Rhapsody"(17자), "Hotel California"(16자)
        assertEquals("Stairway to Heaven", longestTitle.get().getTitle());
    }

    // 13.
    @Test
    public void testGetUpperCaseTitlesOfSongsAboveDuration() {
        List<String> titles = songService.getUpperCaseTitlesOfSongsAboveDuration(400);
        // 400초 이상인 곡: Stairway to Heaven(482)
        assertEquals(1, titles.size());
        assertEquals("STAIRWAY TO HEAVEN", titles.get(0));
    }

    // 14.
    @Test
    public void testMapSongs() {
        List<String> titles = songService.mapSongs(Song::getTitle);
        // [Bohemian Rhapsody, Imagine, Hotel California, Stairway to Heaven, Shape of You]
        assertEquals(5, titles.size());
        assertTrue(titles.contains("Imagine"));
    }

    // 15.
    @Test
    public void testGetAllSongTitlesToString() {
        String allTitles = songService.getAllSongTitlesToString();
        // "Bohemian Rhapsody, Imagine, Hotel California, Stairway to Heaven, Shape of You"
        assertTrue(allTitles.contains("Bohemian Rhapsody"));
        assertTrue(allTitles.contains("Shape of You"));
        assertTrue(allTitles.contains("Hotel California"));
    }

    // 16-1.
    @Test
    public void testGetAverageDurationByGenre() {
        Map<String, Double> avgMap = songService.getAverageDurationByGenre();
        // Rock: (354 + 391 + 482) / 3 = 409
        // Pop: (183 + 233) / 2 = 208
        assertEquals(409, avgMap.get("Rock"), 0.01);
        assertEquals(208, avgMap.get("Pop"), 0.01);
    }

    // 16-2.
    @Test
    public void testGetGenresSortedByAverageDuration() {
        List<Map.Entry<String, Double>> sorted = songService.getGenresSortedByAverageDuration();
        // Rock(409), Pop(208)
        assertEquals("Rock", sorted.get(0).getKey());
        assertEquals("Pop", sorted.get(1).getKey());
    }

    // 17.
    @Test
    public void testFilterSongs_1() {
        List<Song> filtered = songService.filterSongs_1(s -> s.getRating() > 4.8);
        // 4.8보다 큰 곡: Bohemian Rhapsody(4.9), Stairway to Heaven(4.9)
        assertEquals(2, filtered.size());
        assertTrue(filtered.stream().allMatch(s -> s.getRating() > 4.8));
    }

    // 18.
    @Test
    public void testFilterSongs_2() {
        List<Song> filtered = songService.filterSongs_2(s -> s.getTitle().startsWith("H"));
        // "Hotel California"만 해당
        assertEquals(1, filtered.size());
        assertEquals("Hotel California", filtered.get(0).getTitle());
    }

    // 19.
    @Test
    public void testProcessSongs() {
        final int[] count = {0};
        songService.processSongs(s -> {
            // 300초 이상인 곡 개수 세기

        });
        // Bohemian Rhapsody, Hotel California, Stairway to Heaven -> 총 3곡
        assertEquals(3, count[0]);
    }

    // 20.
    @Test
    public void testSortSongs() {
        // 아래 sortSongs 파라미터 작성
        songService.sortSongs(null);
        List<Song> sorted = songService.getSongs();
        // 내림차순: [Stairway to Heaven(482), Hotel California(391), Bohemian Rhapsody(354), Shape of You(233), Imagine(183)]
        assertEquals("Stairway to Heaven", sorted.get(0).getTitle());
        assertEquals("Imagine", sorted.get(sorted.size() - 1).getTitle());
    }

    // 21.
    @Test
    public void testGetAverageDuration() {
        double avgDuration = songService.getAverageDuration();
        // 전체 5곡 총합 1643, 평균은 1643 / 5 = 328.6
        assertEquals(328.6, avgDuration, 0.1);
    }
}
