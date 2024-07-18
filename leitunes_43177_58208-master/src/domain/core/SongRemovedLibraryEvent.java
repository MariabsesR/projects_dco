/**
 * 
 */
package domain.core;

import domain.facade.ISong;


/**
 * Class which the objects represent an event when a song has been removed from
 * library, extends {@code SongLibraryEvent}
 *
 *@author Maria Rocha n58208
 *@author Sara Canhoto n43177
 */
public class SongRemovedLibraryEvent extends SongLibraryEvent {

	/**
	 * Constructor that creates a  SongRemovedLibraryEvent
	 * related to the given song
	 * @param song that was removed from the library
	 */
	public SongRemovedLibraryEvent(ISong song ) {
		super(song);
	}

}
