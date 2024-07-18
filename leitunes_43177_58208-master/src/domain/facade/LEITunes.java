package domain.facade;

import domain.core.MusicLibrary;
import domain.playlists.PlaylistList;

/**
 * Class which its objects contain a MusicLibrary, playlistList and each of the
 * controllers (MusicLibraryController,PlaylistListController)
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto n43177
 *
 */
public class LEITunes {

	private PlaylistListController playlistController;
	private MusicLibraryController libraryController;
	private PlaylistList playlistList;
	private MusicLibrary library;

	/**
	 * Creates the object by initializing the MusicLibrary
	 * ,playlistList,MusicLibraryController andPlaylistListController
	 */
	public LEITunes() {
		this.library = new MusicLibrary();
		this.playlistList = new PlaylistList(library);
		this.playlistController = new PlaylistListController(playlistList,
				library);
		this.libraryController = new MusicLibraryController(library);
	}

	/**
	 * Returns the playlistListController
	 * 
	 * @return object associated PlaylistListController
	 */
	public PlaylistListController getPlaylistController() {
		return playlistController;
	}

	/**
	 * Returns the MusicLibraryController
	 * 
	 * @return object associated MusicLibraryController
	 */
	public MusicLibraryController getMusicLibraryController() {
		return libraryController;
	}
}
