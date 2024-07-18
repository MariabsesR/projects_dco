package domain.playlists;

import domain.core.MusicLibrary;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;

/**
 * Class which objects represent a SmartPlaylist, and it contains 5 songs which
 * where the latest to be added to the library, Class extends SmartPlaylist
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto nº43177
 */
public class MostRecentlyAddedSongsPlaylist extends SmartPlaylist {
	/**
	 * Constructor that creates a empty MostlikedSongsPlaylist associated with
	 * the given library and has {@code name == "Most Recently Added" }
	 * 
	 * @param library MusicLibrary that contains the song that may be in the
	 *                playlist
	 */
	protected MostRecentlyAddedSongsPlaylist(MusicLibrary library) {
		super("Most Recently Added", library);
	}

	@Override
	public void processEvent(SongLibraryEvent e) {
		if (e instanceof SongAddedLibraryEvent) {

			if (library.size() > NUMBER_SONGS) {
				organizeMostRecentlyAdded();
			} else
				addAutomatic(e.getSong());

		} else if (e instanceof SongRemovedLibraryEvent
				&& playlist.contains(e.getSong())) {

			if (library.size() > NUMBER_SONGS) {
				organizeMostRecentlyAdded();
			} else
				removeAutomatic(findIndexSong(e.getSong()));
			organizeMostRecentlyAdded();
		}

	}

	/**
	 * Method that organizes our playlist with the songs that were added last to
	 * the MusicLibrary associated with the playlist
	 */
	private void organizeMostRecentlyAdded() {

		if (size() != 0)
			playlist.clear();

		int index = library.size() - NUMBER_SONGS;
		for (int i = index; i < library.size(); i++) {
			playlist.add(library.get(i));
		}

	}
}
