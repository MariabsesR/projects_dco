package domain.core;

import domain.facade.ISong;
import util.observer.Event;

/**
 * Abstract class that extends {@code Event} e represent an generic type
 * of event about a library, loads information about the song and library
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto n43177
 *
 */
public abstract class SongLibraryEvent implements Event {
	
	protected ISong song;

	/**
	 * Constructs an object of the type SongLibraryEvent related
	 * to the specified song
	 */
	protected SongLibraryEvent(ISong song) {
       this.song=song;
	}

	/**
	 * Returns the song related to the event
	 * 
	 * @return song related to event
	 */
	public ISong getSong() {
		return song;
	}
}
