package util.adts;

import java.util.ArrayList;



/**
 * Class that extends {@code  AbsQListWithSelection<E>} and provides an
 * implementation of {@code  QListWithSelection<E>} based on arrays. This class
 * doesn't admit to being extended.
 * 
 * @author Maria Rocha nº58208
 * @author Sara Canhoto nº43177
 *
 * @param <E> represents the type of the elements in the list
 */

public final class ArrayQListWithSelection<E> extends AbsQListWithSelection<E> {

	/**
	 * Constructor for ArrayQListWithSelection, initializes a list of the type
	 * {@code ArrayList<E>} and makes it so no element is selected
	 */
	public ArrayQListWithSelection() {
		super(new ArrayList<>());
	}


	@Override
	public boolean contains(E e) {
		return list.contains(e);
	}

}
