package util.adts;
/**
 * Class that represents lists of {@code QList<E>} which may have 0 or one element selected and allow the remove 
 * of such element. Extends {@code QList<E>}
 * 
 * @author Maria Rocha nº58208
 * @author Sara Canhoto
 *
 * @param <E> represents the type of the elements in the list
 */
public interface QListWithSelection<E> extends QList<E> {

	/**
	 * Selects the element in the i position of the list
	 * 
	 * @requires {@code 0<=i<size()}   
	 * @param i the position of the element to select
	 */
	public void select(int i);

	@Override
	public void add(E e);

	/**
	 * Checks if an element in the list is selected
	 * 
	 * @return an element in the list is selected
	 */
	public boolean someSelected();

	/**
	 * Returns the index of the selected element in the list 
	 * 
	 * @requires someSelected()
	 * @return the index of the selected element
	 */
	public int getIndexSelected();

	/**
	 * if {@code getIndexSelected()<size()-1;} selects the element in the list
	 * with the index {@code getIndexSelected()+1}, else it makes it so no
	 * element is selected
	 * 
	 * 
	 */
	public void next();

	/**
	 * if {@code getIndexSelected()>0;} selects the element in the list with the
	 * index {@code getIndexSelected()-1}, else it makes it so no element is
	 * selected
	 */
	public void previous();

	/**
	 * Deletes the selected element, if {@code someSelected()}
	 * If {@code someSelected()} returns false, nothing is done
	 */
	public void remove();
   /**
    * Returns the selected element
    * 
    * @requires {@code someSelected()}
    * @return element selected          
    */
	public E getSelected();
	
	public boolean contains (E e);
}
