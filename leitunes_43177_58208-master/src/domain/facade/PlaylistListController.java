package domain.facade;

import java.util.Iterator;
import domain.core.MusicLibrary;
import domain.playlists.ManuallPlaylist;
import domain.playlists.Playlist;
import domain.playlists.PlaylistList;

/**
 * Class where the objects represent a controller of the interactions with an
 * object of the type PlaylistList
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto n43177
 *
 */
public class PlaylistListController {

	private PlaylistList playlists;
	private MusicLibrary library;

	/**
	 * Constructor that creates a PlaylistListController associated with the
	 * given library
	 * 
	 * @param library MusicLibrary associated with the controller
	 */
	public PlaylistListController(PlaylistList playlists,
			MusicLibrary library) {
		this.playlists = playlists;
		this.library = library;
	}

	/**
	 * Inserts a new manual playlist with the given name, in the controlled
	 * PlaylistList the new playlist is now the selected one
	 * 
	 * @param name
	 */
	public void createPlaylist(String name) {
		playlists.add(new ManuallPlaylist(library, name));
		playlists.select(playlists.size() - 1);

	}

	/**
	 * Selects the playlist in the controlled PlaylistList if
	 * {@code  0=<i<size()}, else doesn't do anything
	 * 
	 * @param i
	 */
	public void selectPlaylist(int i) {
		if (i >= 0 && i < playlists.size()) {
			playlists.select(i);
		}
	}

	/**
	 * Indicates if any playlist is selected in the controlled PlaylistList
	 * 
	 * 
	 * @return a playlist is selected
	 */
	public boolean somePlaylistSelected() {
		return playlists.someSelected();
	}

	/**
	 * Returns the selected playlist in the controlled PlaylistList
	 * 
	 * @requires {@code somePlaylistSelected()}
	 * @return selected playlist
	 */
	public Playlist getSelectedPlaylist() {
		return playlists.getSelected();
	}

	/**
	 * if a playlist is selected in controlled PlaylistList removes it, if not
	 * doesn't do anything
	 */
	public void removePlaylist() {
		if (playlists.someSelected()) {
			playlists.remove();
		}
	}

	/**
	 * Returns an iterable structure with the playlist contained in the
	 * controlled playlistList
	 * 
	 * @return iterable structure
	 */
	public Iterator<Playlist> iterator() {
		return playlists.iterator();
	}

	/**
	 * Returns the amount of songs in the selected playlist in the controlled
	 * PlaylistList
	 * 
	 * @requires {@code somePlaylistSelected()}
	 * @return number of songs in selected playlist
	 */
	public int numberOfSongs() {
		return playlists.getSelected().size();
	}

	/**
	 * Inserts the selected song in the library in the selected playlist from
	 * the controlled PlaylistList (if possible. That song is now the selected
	 * song. If there is no selected music in the library it does nothing
	 * 
	 * @requires somePlaylistSelected()
	 */
	public void addSong() {
		if (library.someSelected()) {
			Playlist list = playlists.getSelected();
			list.add(library.getSelected());
			list.select(list.size() - 1);
		}
	}

	/**
	 * if {@code 0<=i<getSelectedPlaylist().numberOfSongs()}, selects the song
	 * in the i index in the selected playlist from the controlled playlistList
	 * 
	 * @requires {@code  somePlaylistSelected()}
	 * @param i index of the song that will be selected
	 */
	public void selectSong(int i) {
		if (i < numberOfSongs())
			playlists.getSelected().select(i);
	}

	/**
	 * Verifies if {@code somePlaylistSelected()} and if so checks
	 * {@code  getSelectedPlayList().someSelected()}
	 * 
	 * @return both condition have been met
	 */
	public boolean someSongSelected() {
		return somePlaylistSelected() && getSelectedPlaylist().someSelected();
	}

	/**
	 * Removes the selected song in the selected playlist in the controlled
	 * playlistList
	 * 
	 * @requires {@code someSongSelected()}
	 */
	public void removeSelectedSong() {
		getSelectedPlaylist().remove();
	}

	/**
	 * Selects the next song in the selected playlist in the controlled
	 * playlistList
	 * 
	 * @requires {@code someSongSelected()}
	 */
	public void nextSong() {
		getSelectedPlaylist().next();
	}

	/**
	 * Selects the previous song in the selected playlist in the controlled
	 * playlistList
	 * 
	 * @requires {@code someSongSelected()}
	 */
	public void previousSong() {
		getSelectedPlaylist().previous();
	}

	/**
	 * In case a song is selected in the playlist selected in the controlled
	 * playlistList, interrupts the song playing (if any is playing), and starts
	 * playing the currently selected song in the controlled MusicLibrary. In
	 * case the song is played till it ends then the song play times will be
	 * incremented. If no song is select nothing will be done
	 */

	public void play() {
		if (someSongSelected()) {
			getSelectedPlaylist().play();
			// the act of playing the songs after the selected song is
			// has ended playing, is done by
			// processing the events of the player in the playlist class
		}
	}

	/**
	 * if a song was played through a playlist in the controlled playlistList,
	 * it stops said song else doesn't do anything
	 */
	public void stop() {
		if (playlists.isPlaying())
			playlists.stop();
	}

	/**
	 * Returns a visual representation of the PlaylistListController
	 * 
	 * @return visual representation
	 */

	@Override
	public String toString() {
		return playlists.toString();
	}

}
