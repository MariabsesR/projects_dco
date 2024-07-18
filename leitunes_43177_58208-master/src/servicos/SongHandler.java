package servicos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Genres;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import domain.core.Song;
import domain.core.SongMetaInfo;

import util.adts.ArrayQListWithSelection;
import util.adts.QListWithSelection;

/**
 * Class that uses the Song path
 * {@code System.getProperty("user.dir")+File.separator+"songs"} to retrieve the
 * mp3 files in the location and create objects of the Song type and associate
 * them to the meta info retrieved from the mp3 files
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto
 *
 */
public abstract class SongHandler implements QListWithSelection<Song> {

	private final String SONGPATH = System.getProperty("user.dir")
			+ File.separator + "songs";
	

	/**
	 * Retrieves the files from the file associated with the path
	 * {@code System.getProperty("user.dir")+File.separator+"songs"} and creates
	 * a QlistWithSelection<Song> with the mp3 files inside the folder
	 */
	public void getFiles() {
		QListWithSelection<Song> list = new ArrayQListWithSelection<>();
		File folder = new File(SONGPATH);
		for (File file : folder.listFiles()) {
			list.add(createSong(file.getPath()));
		}

	}

	/**
	 * Gets the known meta info from the file and creates a Song from the mp3
	 * file
	 * 
	 * @param file path of the mp3 file which to create the Song with
	 * @return Song created
	 */
	public Song createSong(String file) {
		Mp3File mp3file;
		Song song = null;
		String un = "unknown";
		try {
			mp3file = new Mp3File(file);
			if (mp3file.hasId3v2Tag()) {
				ID3v2 id3v2Tag = mp3file.getId3v2Tag();
				List<String> artists = new ArrayList<>();
				artists.add(id3v2Tag.getArtist());

				// check genre
				int index = id3v2Tag.getGenre();
				String genre = (index != -1) ? ID3v1Genres.GENRES[index] : un;

				// check title
				String title = id3v2Tag.getTitle();
				if (title == null)
					title = un;
				// check album
				String album = id3v2Tag.getAlbum();
				if (album == null)
					album = un;

				SongMetaInfo info = new SongMetaInfo(title, genre, artists,
						album);
				song = new Song(info, file);
			} else if (mp3file.hasId3v1Tag()) {
				ID3v1 id3v1Tag = mp3file.getId3v1Tag();
				List<String> artists = new ArrayList<>();
				artists.add(id3v1Tag.getArtist());

				// check genre
				int index = id3v1Tag.getGenre();
				String genre = (index != -1) ? ID3v1Genres.GENRES[index] : un;

				// check title
				String title = id3v1Tag.getTitle();
				if (title == null)
					title = un;
				// check album
				String album = id3v1Tag.getAlbum();
				if (album == null)
					album = un;

				SongMetaInfo info = new SongMetaInfo(title, genre, artists,
						album);
				song = new Song(info, file);
			} else {
				SongMetaInfo info = new SongMetaInfo(null, null, null, null);
				song = new Song(info, file);

			}
		} catch (UnsupportedTagException | InvalidDataException
				| IOException e) {
			e.printStackTrace();
		}
		return song;

	}

}
