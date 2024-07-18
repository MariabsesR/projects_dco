package domain.playlists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.core.MusicLibrary;
import util.adts.QListWithSelection;

/**
 * Class which objects represent a list of playlists of a MusicLibrary. At each
 * moment at max one of the playlist may be selected. Class implements
 * {@code QListWithSelection<Playlist>}
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto nº43177
 */
public class PlaylistList implements QListWithSelection<Playlist> {

	private List<Playlist> list;
	private int selected;
	private boolean isPlaying;
	private Playlist currentPlaylist;

	/**
	 * Constructor that creates an empty playlistList associated with the given
	 * musicLibrary, and creates one of each type of SmartPlaylist inside the
	 * playlistList
	 * 
	 * @param library associated MusicLibrary that will be associated with the
	 *                playlist inside playlistlist
	 */
	public PlaylistList(MusicLibrary library) {
		list = new ArrayList<>();
		list.add(new MostLikedSongsPlaylist(library));
		list.add(new MostRecentlyAddedSongsPlaylist(library));
		isPlaying = false;
		selected = -1;
		currentPlaylist = null;
	}


	/**
	 * If there is a selected song in the playlist selected in playlistList,
	 * determines the interruption of the song in case any is playing, and starts
	 * playing by order all the musics in the playlist starting at the selected song
	 * 
	 * @requires {@code someSelected()}
	 */
	public void play() {
		if (isPlaying) {
			currentPlaylist.stop();
			isPlaying = false;
		}

		currentPlaylist = list.get(selected);
		if (currentPlaylist.someSelected()) {
			currentPlaylist.play();
			isPlaying = true;
		}
	}

	/**
	 * Indicates if any song was put to play from the playlists
	 * 
	 * @return
	 */
	public boolean isPlaying() {
		return isPlaying;
	}

	/**
	 * Stops the music being played
	 * 
	 * @requires{@code isPlaying()}
	 */
	public void stop() {
		currentPlaylist.stop();
		isPlaying = false;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Playlist get(int i) {
		return list.get(i);
	}

	@Override
	public Iterator<Playlist> iterator() {
		List<Playlist> lista = new ArrayList<>();
		for (Playlist p : lista)
			lista.add(p);
		return lista.iterator();
	}

	@Override
	public void select(int i) {
		selected = i;
	}

	@Override
	public void add(Playlist e) {
		list.add(e);
	}

	@Override
	public boolean someSelected() {
		return selected != -1;
	}

	@Override
	public int getIndexSelected() {
		return selected;
	}

	@Override
	public void next() {
		selected++;
	}

	@Override
	public void previous() {
		selected--;
	}

	@Override
	public void remove() {
		list.remove(selected);
	}

	@Override
	public Playlist getSelected() {
		return list.get(selected);
	}

	@Override
	public boolean contains(Playlist e) {
		return list.contains(e);
	}

	/**
	 * Returns a visual representation of playlistList
	 * 
	 * @return visual representation
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("***** PLAYLISTS *****\n");
		for (int i = 0; i < list.size(); i++) {
			str.append(list.get(i).toString());
		}
		return str.toString();
	}
}
