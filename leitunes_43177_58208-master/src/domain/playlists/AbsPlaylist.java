package domain.playlists;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import domain.core.MusicLibrary;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;

/**
 * Abstract class that represent a playlist, implements Playlist
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto nº43177
 *
 */

public abstract class AbsPlaylist implements Playlist {

	protected MusicLibrary library;
	protected String name;
	protected List<ISong> playlist;
	protected int selected;
	protected Player player;
	protected boolean playing;
	private ISong currentSong;

	/**
	 * Constructs an empty AbsPlaylist that will contain songs of the given
	 * MusicLibrary and has the given name
	 * 
	 * @param library MusicLibrary of music that may be in the playlist
	 * @param name    name of the playlist
	 */
	protected AbsPlaylist(MusicLibrary library, String name) {
		this.library = library;
		this.name = name;
		playlist = new ArrayList<>();
		player = PlayerFactory.INSTANCE.getPlayer();
		selected = -1;
		library.registerListener(this);
		player.addListener(this);
		currentSong = null;
	}

	@Override
	public Iterator<ISong> iterator() {
		List<ISong> songs = new ArrayList<>();
		for (ISong s : playlist)
			songs.add(s);
		return songs.iterator();
	}

	@Override
	public int size() {
		return playlist.size();
	}

	@Override
	public ISong getSelected() {
		return playlist.get(selected);
	}

	@Override
	public boolean someSelected() {
		return selected != -1;
	}

	@Override
	public boolean add(ISong song) {
		boolean re = false;
		if (!playlist.contains(song)) {
			playlist.add(song);
			selected = playlist.size() - 1;
			re = true;
		}
		return re;
	}

	@Override
	public boolean remove() {
		if (someSelected()) {
			if (playlist.contains(library.getSelected())) {
				playlist.remove(library.getSelected());
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void select(int i) {
		selected = i;
	}

	@Override
	public int getIndexSelected() {
		return selected;
	}

	@Override
	public void next() {
		if (getIndexSelected() < (playlist.size() - 1))
			select(getIndexSelected() + 1);
		else
			select(-1);
	}

	@Override
	public void previous() {
		if (getIndexSelected() > 0)
			select(getIndexSelected() - 1);
		else
			select(-1);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isPlaying() {
		return playing;
	}

	@Override
	public void play() {
		if (playing) {
			stop();
			playing = false;
		}
		currentSong = getSelected();
		player.load(currentSong.getFilename());
		player.play();
		playing = true;

	}

	@Override
	public void stop() {
		if (isPlaying()) {
			player.stop();
			playing = false;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (isPlaying()) {
			if (evt.getPropertyName().equals("playingState")
					&& evt.getNewValue().equals(Player.PlayingState.ENDED)) {
				currentSong.incTimesPlayed();
				if (playlist.indexOf(currentSong) != selected) {
					play();
				} else if (playlist.indexOf(currentSong) < playlist.size()
						- 1) {
					next();
					play();
				}

			}
			if (evt.getNewValue().equals(Player.PlayingState.STOPED)) {
				playing = false;
			}
		}
	}

	@Override
	public void processEvent(SongLibraryEvent e) {

		if (e instanceof SongRemovedLibraryEvent) {
			playlist.remove(e.getSong());
		}

	}

	/**
	 * Returns the visual representation of an AbsPlaylist
	 * 
	 * @return visual representation
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("*-- Playlist " + name + " --* \n");
		for (int i = 0; i < playlist.size(); i++) {
			str.append(i + " " + playlist.get(i).toString());
		}
		return str.toString();
	}

}
