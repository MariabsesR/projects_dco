package domain.core;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import domain.facade.ISong;
import util.adts.RegExpMatchable;

//FAZER JUNIT
/**
 * Class that represents Songs, each song must have a file name where the music
 * is located (with format mp3), meta-information, how many times the song has
 * been played, and a classification. Class implements
 * {@code ISong && RegExpMatchable}
 * 
 * @author Maria Rocha nº58208
 * @author Sara Canhoto n43177
 *
 */
public class Song implements RegExpMatchable, ISong, Comparable<Song> {
	private Rate rate;
	private SongMetaInfo meta;
	private String fileName;
	private int playTimes;

	/**
	 * Constructor that creates a song, with the info provided, and the name of
	 * the file provided, Initializes the classification of the song as 0 and
	 * its play times as 0 too
	 * 
	 * @param info     characteristics of the song such as title, genre,artists,
	 *                 album name
	 * @param fileName name of the file where the song is located
	 */
	public Song(SongMetaInfo info, String fileName) {

		meta = info;
		this.fileName = fileName;
		playTimes = 0;
		rate = new Rate(0);

	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if (!(obj instanceof Song)) return false;
		Song s = (Song) obj;
		return this.rate.equals(s.rate) && this.meta.equals(s.meta)
				&& this.fileName.equals(s.fileName)
				&& this.playTimes == s.playTimes;
	}

	@Override
	public boolean matches(String regexp) {
		Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(toString());

		return matcher.find();
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (rate != null ? rate.hashCode() : 0);
		hash += (meta != null ? meta.hashCode() : 0);
		hash += (fileName != null ? fileName.hashCode() : 0);
		hash += playTimes;
		return hash;
	}

	@Override
	public String toString() {
		return meta.toString() + " --- " + rate.toString()
				+ " -- " + playTimes + "\n";
	}

	@Override
	public void incTimesPlayed() {
		playTimes++;
	}

	@Override
	public int getTimesPlayed() {
		return playTimes;
	}

	@Override
	public Rate getRating() {
		return rate;
	}

	@Override
	public void incRating() {
		rate = new Rate(rate.getRate() + 1);

	}

	@Override
	public void decRating() {
		rate = new Rate(rate.getRate() - 1);

	}

	@Override
	public String getSongTitle() {
		return meta.title();
	}

	@Override
	public String getGenre() {
		return meta.genre();
	}

	@Override
	public List<String> getArtists() {
		return meta.artists();
	}

	@Override
	public String getAlbum() {
		return meta.albumName();
	}

	@Override
	public String getFilename() {
		return fileName;
	}

	@Override
	public int compareTo(Song o) {
		return this.rate.compareTo(o.rate);
	}

}
