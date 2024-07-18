package util.adts;

import java.util.Iterator;
import java.util.List;

/**
 * Class that implements {@code QListWithSelection<E>} and offers a skeleton
 * implementation with a constructor that triggers the creation of the list,
 * without compromising to a specific implementation of that list
 *
 * @author Maria Rocha nº58208
 * @author Sara Canhoto nº43177
 *
 * @param <E> represents the type of the elements in the list
 */
public abstract class AbsQListWithSelection<E>
		implements QListWithSelection<E> {

	protected List<E> list;
	protected int selected;

	/**
	 * Constructor for AbsQListWithSelection, receives the type of list that
	 * should be constructed, makes it so no element is selected
	 * 
	 * @param list the type of list that should be constructed
	 */
	protected AbsQListWithSelection(List<E> list) {
		selected = -1;
		this.list = list;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public E get(int i) {
		return list.get(i);
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public void select(int i) {
		selected = i;
	}

	@Override
	public void add(E e) {
		list.add(e);
		selected = list.size() - 1;
	}

	@Override
	public boolean someSelected() {
		return selected != -1;
	}

	@Override
	public int getIndexSelected() {
		return selected;
	}

	@Override
	public void next() {
		if (getIndexSelected() < (size() - 1)) {
			selected++;
		} else {
			selected = -1;
		}

	}

	@Override
	public void previous() {
		if (getIndexSelected() > 0) {
			selected--;
		} else {
			selected = -1;
		}
	}

	@Override
	public void remove() {
		if (someSelected()) {
			list.remove(selected);
			selected = -1;
		}
	}

	@Override
	public E getSelected() {
		return list.get(selected);
	}

}
