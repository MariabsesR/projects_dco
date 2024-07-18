package domain.facade;

import java.util.Optional;

import domain.core.MusicLibrary;

/**
 * Class where the objects represent a controller of the interactions with an
 * object of the type MusicLibrary
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto n43177
 *
 */
public class MusicLibraryController {

	private MusicLibrary library;

	/**
	 * Constructor that creates a MusicLibraryController associated with the
	 * given library
	 * 
	 * @param library MusicLibrary associated with the controller
	 */
	MusicLibraryController(MusicLibrary library) {
		this.library = library;
	}

	/**
	 * Returns the size of the library associated with the
	 * MusicLibraryController
	 * 
	 * @return size of the library associated
	 */
	public int numberOfSongs() {
		return library.size();
	}

	/**
	 * Creates and adds the song to the associated MusicLibrary by obtaining the
	 * meta-information of the file(if it exists)
	 * 
	 * @requires filename is a mp3file
	 * @param filename
	 */
	public void addSong(String filename) {
		library.add(library.createSong(filename));
	}

	/**
	 * Selects the song in the controlled MusicLibrary if
	 * {@code 0<=i<numberOfSongs()}
	 * 
	 * @param i index in the library of the song to select
	 */
	public void selectSong(int i) {
		if (i >= 0 && i < numberOfSongs()) {
			// selects the song in the library
			library.select(i);
		}
	}

	/**
	 * Returns the selected song of the controlled MusicLibrary if it exists
	 * 
	 * @return selected song in the controlled MusicLibrary
	 */
	public Optional<ISong> getSelectedSong() {
		return Optional.of(library.getSelected());
	}

	/**
	 * Removes the selected song in the controlled MusicLibrary
	 */
	public void removeSelectedSong() {
		library.remove();
	}

	/**
	 * In case a song is selected in the controlled MusicLibrary, interrupts the
	 * song playing (if any is playing), and starts playing the currently
	 * selected song in the controlled MusicLibrary. In case the song is played
	 * till it ends then the song play times will be incremented. If no song is
	 * select nothing will be done
	 */
	public void play() {
		if (library.someSelected()) {
			library.play();
			// when a song is played until the end the player event will
			// increment the times played
		}
	}

	/**
	 * if a song was played through the controlled MusicLibrary, it stops said
	 * song else doesn't do anything
	 */
	public void stop() {
		if (library.isPlaying())
			library.stop();
	}

	/**
	 * If there is a selected song in the controlled MusicLibrary, it increments
	 * that song rate to the value right above it(in case the rate is already
	 * the maximum it is maintained)
	 */
	public void incRateSelected() {
		if (library.someSelected())
			library.incRateSelected();
	}

	/**
	 * If there is a selected song in the controlled MusicLibrary, it decrements
	 * that song rate to the value right below it(in case the rate is already
	 * the minimum it is maintained)
	 */
	public void decRateSelected() {
		if (library.someSelected())
			library.decRateSelected();
	}

	/**
	 * Returns an iterable structure with the musics contained in the controlled
	 * MusicLibrary that match the given expression
	 * 
	 * @param reexp expression to match the songs to
	 * @return iterable structure
	 */
	public Iterable<ISong> getMatches(String reexp) {
		return library.getMatches(reexp);
	}

	/**
	 * Returns an iterable structure with the songs contained in the controlled
	 * MusicLibrary
	 * 
	 * @return iterable structure
	 */
	public Iterable<ISong> getSongs() {
		return library.getSongs();
	}

	/**
	 * Returns a visual representation of the MusicLibraryController
	 * 
	 * @return visual representation
	 */
	@Override
	public String toString() {
		return library.toString();
	}

}
