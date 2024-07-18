package domain.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import servicos.SongHandler;
import util.adts.ArrayQListWithSelection;
import util.adts.QListWithSelection;
import util.observer.Listener;
import util.observer.Subject;

/**
 * Class which objects represent library of songs, at each moment a maximum of
 * one of the songs in the library may be selected. Class implements
 * {@code QListWithSelection<Song>, Subject<SongLibraryEvent>, PropertyChangeListener}
 * and extends {@code SongHandler}
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto n43177
 *
 */
public class MusicLibrary extends SongHandler
		implements QListWithSelection<Song>, Subject<SongLibraryEvent>,
		PropertyChangeListener {

	private boolean playing;
	private Player player;
	private QListWithSelection<Song> list;
	private List<Listener<SongLibraryEvent>> listeners;
	private Song currentSong;

	/**
	 * Constructor that initializes a MusicLibrary with the songs in the path
	 * {@code "\songs"}
	 *
	 */
	public MusicLibrary() {
		player = PlayerFactory.INSTANCE.getPlayer();
		list = new ArrayQListWithSelection<>();
		getFiles();
		listeners = new ArrayList<>();
		player.addListener(this);
		currentSong = null;
	}

	/**
	 * Determines the interruption of the song playing if {@code isPlaying()}
	 * and starts playing the song selected
	 * 
	 * @requires {@code someSelected()}
	 */
	public void play() {
		if (playing) {
			player.stop();
			playing = false;
		}
		currentSong = list.getSelected();
		player.load(currentSong.getFilename());
		player.play();
		playing = true;

	}

	/**
	 * Indicates if a song is playing, if said song was put to play through the
	 * library
	 * 
	 * @return song is playing
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * Stops the music that is currently playing
	 *
	 * @requires {@code isPlaying()}
	 */
	public void stop() {
			player.stop();
			playing = false;
	}

	/**
	 * Increments the classification of the selected song in the music library,
	 * to the value immediately up, if that value is the max it says the same
	 */
	public void incRateSelected() {
		list.getSelected().incRating();
		emitEvent(new SongRatedLibraryEvent(list.getSelected()));
	}

	/**
	 * Decrements the classification of the selected song in the music library,
	 * to the value immediately down, if that value is the min it says the same
	 */
	public void decRateSelected() {
		list.getSelected().decRating();
		emitEvent(new SongRatedLibraryEvent(list.getSelected()));
	}

	/**
	 * Returns an iterable structure with the songs in the MusicLibrary that
	 * match the given expression
	 * 
	 * @param reexp expression that the songs must match to
	 * @return iterable structure
	 */
	public Iterable<ISong> getMatches(String reexp) {
		List<ISong> regSongs = new ArrayList<>();
		for (ISong s : list) {
			if (s.matches(reexp))
				regSongs.add(s);
		}
		return regSongs;
	}

	/**
	 * Returns an iterable structure with the songs contained in the
	 * MusicLibrary
	 * 
	 * @return iterable structure
	 */
	public Iterable<ISong> getSongs() {
		List<ISong> regSongs = new ArrayList<>();
		for (ISong s : list)
			regSongs.add(s);
		return regSongs;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Song get(int i) {
		return list.get(i);
	}

	@Override
	public Iterator<Song> iterator() {
		return list.iterator();
	}

	@Override
	public void select(int i) {
		list.select(i);
	}

	@Override
	public void add(Song e) {
		list.add(e);
		emitEvent(new SongAddedLibraryEvent(e));
	}

	@Override
	public boolean someSelected() {
		return list.someSelected();
	}

	@Override
	public int getIndexSelected() {
		return list.getIndexSelected();
	}

	@Override
	public void next() {
		list.next();
	}

	@Override
	public void previous() {
		list.previous();
	}

	@Override
	public void remove() {
		if (list.someSelected()) {
			emitEvent(new SongRemovedLibraryEvent(list.getSelected()));
			list.remove();
		}
	}

	@Override
	public Song getSelected() {
		return list.getSelected();
	}

	/**
	 * Receives a song and returns its index in the MusicLibrary
	 * 
	 * @param song the song we wish to find the index of
	 * @return index of the wanted song
	 */
	public int findIndexSong(Song song) {
		int count = 0;
		for (int i = 0; i < list.size() && !list.get(i).equals(song); i++) {
			count++;
		}
		return count;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (isPlaying() &&  (evt.getPropertyName().equals("playingState"))) {
				if (evt.getNewValue().equals(Player.PlayingState.ENDED)) {
					currentSong.incTimesPlayed();
				} else if (evt.getNewValue().equals(Player.PlayingState.STOPED)) {
					playing = false;
				}
		}

	}

	@Override
	public void emitEvent(SongLibraryEvent e) {
		for (Listener<SongLibraryEvent> el : listeners) {
			el.processEvent(e);
		}

	}

	@Override
	public void registerListener(Listener<SongLibraryEvent> obs) {
		listeners.add(obs);

	}

	@Override
	public void unregisterListener(Listener<SongLibraryEvent> obs) {
		listeners.remove(obs);
	}

	@Override
	public boolean contains(Song e) {
		return list.contains(e);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("*****MUSIC LIBRARY****\n");
		for (int i = 0; i < list.size(); i++) {
			str.append(i + " " + list.get(i).toString());
		}
		return str.toString();
	}

}
