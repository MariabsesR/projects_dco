package domain.playlists;

import java.util.Arrays;
import domain.core.MusicLibrary;
import domain.core.Song;
import domain.core.SongLibraryEvent;
import domain.core.SongRatedLibraryEvent;


/**
 * Class which objects represent a SmartPlaylist, and it contains 5 songs which
 * have the highest rating in all the library, Class extends SmartPlaylist
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto nº43177
 *
 */
public class MostLikedSongsPlaylist extends SmartPlaylist {
	/**
	 * Constructor that creates a empty MostlikedSongsPlaylist associated with
	 * the given library and has {@code name == "Most Liked" }
	 * 
	 * @param library MusicLibrary that contains the song that may be in the
	 *                playlist
	 */
	protected MostLikedSongsPlaylist(MusicLibrary library) {
		super("Most Liked", library);
		player.addListener(this);
	}

	@Override
	public void processEvent(SongLibraryEvent e) {
		if (e instanceof SongRatedLibraryEvent) {
			if (playlist.contains(e.getSong())) {
				organizeMostLikedSong();
			} else {
				addAutomatic(e.getSong());
			}
		}
	}

	/**
	 * Method that organizes our playlist with the songs with the highest rating
	 * in the MusicLibrary associated with the playlist. Only songs with rate > 0 
	 * will be added to the playlist
	 */
	private void organizeMostLikedSong() {

		Song[] librarySongs = new Song[library.size()]; // songs from the
														// library
		for (int i = 0; i < library.size(); i++) {
			librarySongs[i] = library.get(i);
		}
		Arrays.sort(librarySongs);// sorts in ascending order
		// if the array is not empty
		if (size() != 0) {
			playlist.clear();
		}

		int initial = librarySongs.length - 1;

		// fill with the most liked from the library
		if (librarySongs.length >= NUMBER_SONGS) { // if the library has more
													// than the constant of
													// songs then we need to
													// sort the library first
													// and get the top

			for (int i = initial; i >= (initial - NUMBER_SONGS); i--) {
				if (librarySongs[i].getRating().getRate() > 0) {
					addAutomatic(librarySongs[i]);
				}

			}
		} else {// if the library has less songs than the constant we add by
				// order
			for (int i = initial; i >= 0; i--) {
				if (librarySongs[i].getRating().getRate() > 0) {
					addAutomatic(librarySongs[i]);
				}

			}
		}

	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("*-- Playlist Most Liked--*\n");
		for (int i = 0; i < playlist.size(); i++) {
			str.append(i + " " + playlist.get(i).toString());
		}
		return str.toString();
	}

}
