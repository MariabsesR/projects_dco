package domain.playlists;

import domain.core.MusicLibrary;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

/**
 * Class which objects represent a playlist, that contains the songs that were
 * manually added to it. this playlist permit the removal and change of the
 * position of the songs Class extends AbsPlaylist
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto nº43177
 *
 */
public class ManuallPlaylist extends AbsPlaylist {
	/**
	 * Constructor that creates a empty ManualPlaylist associated with the given
	 * library and has the given name
	 * 
	 * @param library MusicLibrary that contains the song that may be in the
	 *                playlist
	 * @param name    name of the created playlist
	 */
	public ManuallPlaylist(MusicLibrary library, String name) {
		super(library, name);

	}

	@Override
	public void processEvent(SongLibraryEvent e) {
		if (e instanceof SongRemovedLibraryEvent
				&& playlist.contains(e.getSong())) {

			playlist.remove(e.getSong());
			selected = -1;
		}

	}

	@Override
	public boolean moveUpSelected(int i) {
		ISong selectedSong = getSelected();
		playlist.remove(selectedSong);
		playlist.add(i, selectedSong);
		this.selected = i;
		return someSelected();
	}

}
