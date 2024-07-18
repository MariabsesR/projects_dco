package util.adts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class ArrayQListWithSelectionTest {

	@Test
	public void testSize() {

		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);
		lista.add(10);

		assertTrue(lista.size() == 3);
	}

	@Test
	public void testConstructor() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		assertTrue(lista.size() == 0);
	}

	@Test
	public void testGet() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);
		lista.add(10);

		assertTrue(lista.get(2) == 10);
	}

	@Test
	public void testSelect() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);
		lista.add(10);

		lista.select(0);

		assertTrue(lista.selected != -1);
	}

	@Test
	public void testAdd() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);

		assertTrue(lista.contains(1));
	}

	@Test
	public void testSomeSelected() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);
		lista.add(10);

		lista.select(0);

		assertTrue(lista.someSelected());
	}

	@Test
	public void testGetIndexSelected() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);
		lista.add(10);

		lista.select(0);

		assertTrue(lista.getIndexSelected() == 0);
	}

	@Test
	public void testNext() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);
		lista.add(10);

		lista.select(0);
		lista.next();

		assertTrue(lista.getIndexSelected() == 1);
		lista.next();
		lista.next();

		assertTrue(lista.getIndexSelected() == -1);
	}

	@Test
	public void testPrevious() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);
		lista.add(10);

		lista.select(1);
		lista.previous();

		assertTrue(lista.getIndexSelected() == 0);
		lista.previous();
		assertTrue(lista.getIndexSelected() == -1);
	}

	@Test
	public void testRemove() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);
		lista.add(10);

		lista.select(1);
		lista.remove();
		assertTrue(!lista.contains(5));
		assertTrue(lista.getIndexSelected() == -1);
	}

	@Test
	public void testGetSelected() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);
		lista.select(1);

		assertTrue(lista.getSelected() == 5);
	
	}

	@Test
	public void testIteratorHasNext() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);
		lista.add(10);

		Iterator<Integer> iterador = lista.iterator();
		// method hasNext()
		assertTrue(iterador.hasNext());
		iterador.next();
		iterador.next();
		iterador.next();
		assertFalse(iterador.hasNext());
	}

	@Test
	public void testIteratorNext() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		Iterator<Integer> iterador = lista.iterator();
		assertTrue(iterador.next() == 1);
	}

	@Test
	public void testIteratorRemove() {
		ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
		lista.add(1);
		lista.add(5);

		Iterator<Integer> iterador = lista.iterator();
		iterador.next();
		iterador.remove();
		assertFalse(lista.contains(1));
	}
}
