package domain.playlists;

import domain.core.MusicLibrary;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

/**
 * Abstract class that represents a playlist that do not permit manual addition
 * and removal of songs and don't permit that the selected song is manually
 * moved from its position
 * 
 * @author Maria Rocha n 58208
 * @author Sara Canhoto nº43177
 *
 */
public abstract class SmartPlaylist extends AbsPlaylist implements Playlist {

	protected static final int NUMBER_SONGS = 5;

	@Override
	public boolean moveUpSelected(int i) {
		return false;
	}

	@Override
	public boolean add(ISong song) {
		return false;
	}

	@Override
	public boolean remove() {
		return false;
	}

	/**
	 * Constructor that creates a SmartPlaylist associated with the given
	 * library and has the given name
	 * 
	 * @param name                name of the playlist
	 * @param libraryMusicLibrary that contains the song that may be in the
	 *                            playlist
	 */
	protected SmartPlaylist(String name, MusicLibrary library) {
		super(library, name);

	}

	/*
	 * Used for automatic insertions in the SmartPlaylist
	 */
	protected void addAutomatic(ISong song) {
		super.add(song);
	}

	/**
	 * Used for automatic removal in the SmartPlaylist Selects song with the
	 * given index in the playlist and removes it.
	 * 
	 * @param index song that will be removed
	 */
	protected void removeAutomatic(int index) {
		int saveSelect = selected;
		super.select(index);
		super.remove();
		super.select(saveSelect);
	}

	@Override
	public void processEvent(SongLibraryEvent e) {
		if (e instanceof SongAddedLibraryEvent) {
			addAutomatic(e.getSong());
		} else if (e instanceof SongRemovedLibraryEvent) {
			removeAutomatic(findIndexSong(e.getSong()));
		}
	}

	/**
	 * Receives a song and returns the index of that song in the playlist
	 * 
	 * @param song song we wish to find the index of
	 * @requires {@code playlist.contains(song)}
	 * @return index of the song
	 */
	public int findIndexSong(ISong song) {
		int count = 0;
		for (int i = 0; i < playlist.size()
				&& !playlist.get(i).equals(song); i++) {
			count++;
		}
		return count;
	}



}
