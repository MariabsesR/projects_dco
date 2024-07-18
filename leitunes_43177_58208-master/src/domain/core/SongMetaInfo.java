package domain.core;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.adts.RegExpMatchable;

/**
 * Class that saves information associated with Songs such as title,genre,list
 * of artists, albumName
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto n43177
 *
 */
public record SongMetaInfo(String title, String genre, List<String> artists,
		String albumName) implements RegExpMatchable {

	@Override
	public boolean matches(String regexp) {

		Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(toString());
		return matcher.find();
	}

	/**
	 * Returns visual representation of SongMetaInfo
	 * 
	 * @return visual representation
	 */
	public String toString() {
		return "[" + title + ", " + albumName + ", " + genre + ", "
				+ artists.toString() + "]";
	}
}
