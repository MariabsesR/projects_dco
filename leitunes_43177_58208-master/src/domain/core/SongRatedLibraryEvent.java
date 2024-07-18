/**
 * 
 */
package domain.core;

import domain.facade.ISong;


/**
 * Class which the objects represent an event when a song has a new Rate in a
 * library, extends {@code SongLibraryEvent}
 *
 * @author Maria Rocha n58208
 * @author Sara Canhoto n43177
 */
public class SongRatedLibraryEvent extends SongLibraryEvent {

	/**
	 * Constructor that creates a SongRatedLibraryEvent related to the given
	 * song
	 * 
	 * @param song that received a new rating in the library
	 */
	public SongRatedLibraryEvent(ISong song) {
		super(song);
	}
}
