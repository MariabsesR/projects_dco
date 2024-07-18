package domain.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SongTest {

	@Test
	public void createSongRate0() {
		Song song = new Song(new SongMetaInfo(null, null, new ArrayList<>(), ""), "teste");
		Rate rate = song.getRating();

		assertTrue(rate.getRate() == 0);
	}

	@Test
	public void increaseAndDecreaseRate() {
		Song song = new Song(new SongMetaInfo(null, null, new ArrayList<>(), ""), "teste");
		song.incRating();
		assertTrue(song.getRating().getRate() == 1);

		song.decRating();
		assertTrue(song.getRating().getRate() == 0);
	}

	@Test
	void testRateMaxMin() {
		Song song = new Song(new SongMetaInfo(null, null, new ArrayList<>(), ""), "teste");

		for (int i = 0; i <= 5; i++) {
			song.incRating();
		}

		assertTrue(song.getRating().getRate() == 5);

		for (int i = 0; i <= 5; i++) {
			song.decRating();
		}

		assertTrue(song.getRating().getRate() == 0);
	}

	@Test
	void testEquals() {
		Song song1 = new Song(new SongMetaInfo(null, null, new ArrayList<>(), ""), "teste");
		Song song2 = new Song(new SongMetaInfo(null, null, new ArrayList<>(), ""), "teste");
		Song song3 = new Song(new SongMetaInfo(null, null, new ArrayList<>(), ""), "teste2");

		assertTrue(song1.equals(song2));
		assertFalse(song1.equals(song3));

		assertTrue(song1.hashCode() == song2.hashCode());
		assertTrue(song1.hashCode() != song3.hashCode());
	}

	@Test
	void testMatches() {
		Song song1 = new Song(new SongMetaInfo("ola", null, new ArrayList<>(), ""), "ola");
		Song song2 = new Song(new SongMetaInfo("adeus", null, new ArrayList<>(), ""), "adeus");

		boolean result1 = true;
		boolean result2 = false;

		assertEquals(song1.matches("ola"), result1);
		assertEquals(song2.matches("ola"), result2);

	}

	@Test
	void testIncPlayedTimes() {
		Song song = new Song(new SongMetaInfo(null, null, new ArrayList<>(), ""), "teste");

		assertTrue(song.getTimesPlayed() == 0);
		song.incTimesPlayed();
		assertTrue(song.getTimesPlayed() == 1);

	}

	@Test
	void testgetName() {
		Song song = new Song(new SongMetaInfo("titulo", null, new ArrayList<>(), ""), "teste");

		assertTrue(song.getSongTitle() == "titulo");
	}

	@Test
	void testgetGenre() {
		Song song = new Song(new SongMetaInfo("titulo", "pop", new ArrayList<>(), ""), "teste");

		assertTrue(song.getGenre().equals("pop"));
	}

	@Test
	void testgetArtists() {
		List<String> artistas = new ArrayList<>();
		artistas.add("Sara");
		artistas.add("Maria");

		Song song = new Song(new SongMetaInfo("titulo", "pop", artistas, ""), "teste");

		assertTrue(song.getArtists().get(0).equals("Sara"));
		assertTrue(song.getArtists().get(1).equals("Maria"));
	}

	@Test
	void testgetAlbumName() {
		Song song = new Song(new SongMetaInfo("titulo", "pop", new ArrayList<>(), "teste1"), "teste");

		assertTrue(song.getAlbum().equals("teste1"));
	}

	@Test
	void testgetFilename() {
		Song song = new Song(new SongMetaInfo("titulo", "pop", new ArrayList<>(), "teste1"), "teste");

		assertTrue(song.getFilename().equals("teste"));
	}

	@Test
	void testcompareTo() {
		Song song1 = new Song(new SongMetaInfo("titulo", "pop", new ArrayList<>(), "teste1"), "teste");
		Song song2 = new Song(new SongMetaInfo("titulo2", "pop", new ArrayList<>(), "teste2"), "teste2");
		Song song3 = new Song(new SongMetaInfo("titulo2", "pop", new ArrayList<>(), "teste2"), "teste2");

		assertTrue(song1.compareTo(song2) == 0);
		assertFalse(song1.compareTo(song3) != 0);
	}

}
