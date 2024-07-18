/**
 * 
 */
package domain.core;

import domain.facade.ISong;

/**
 * Class which the objects represent an event when a song is added to
 * a library, extends {@code SongLibraryEvent} 
 *
 *@author Maria Rocha n58208
 *@author Sara Canhoto n43177
 */
public class SongAddedLibraryEvent extends SongLibraryEvent {

	/**
	 * Constructor that creates a  SongAddedLibraryEvent
	 * related to the given song
	 * @param song that was added to the library
	 */
	public SongAddedLibraryEvent(ISong song) {
		super(song);
	}

}
