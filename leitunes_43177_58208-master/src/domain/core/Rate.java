package domain.core;

/**
 * Class which objects are immutable and represent possible values of the
 * classification of a song
 * 
 * @author Maria Rocha n58208
 * @author Sara Canhoto n43177
 *
 */
public class Rate implements Comparable<Rate> {

	private static final int MIN = 0;
	private static final int MAX = 5;
	private final int RATENUMBER;

	/**
	 * Constructor that receives an int i and makes Rate with such value, the
	 * Given int must have a have a value between The defined MIN and MAX, being
	 * the Min equal to 0 and Max equal to 5
	 * 
	 * @param i value of the classification 
	 *         
	 */
	public Rate(int i) {

		if (i < MIN)
			RATENUMBER = 0;
		else if (i > MAX)
			RATENUMBER = 5;
		else
			RATENUMBER = i;
	}

	@Override
	public int compareTo(Rate o) {
		if (RATENUMBER == o.RATENUMBER)
			return 0;
		else if (RATENUMBER > o.RATENUMBER)
			return 1;
		else
			return -1;
	}

	@Override
	public String toString() {
		return Integer.toString(RATENUMBER);
	}

	/**
	 * Returns the int value of the Rate,
	 * 
	 * @return int value of Rate
	 */
	public int getRate() {
		return RATENUMBER;
	}

	@Override
	public int hashCode() { 
		return RATENUMBER;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Rate) )
			return false;

		Rate r = (Rate) obj;
		return this.RATENUMBER == r.RATENUMBER;
	}

}
