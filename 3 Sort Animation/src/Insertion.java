/*
 * Jessica Krynitsky
 * jmk8vr
 * citations: 
 * 	Big Java Early Objects
 * 	stackoverflow for swap method, 
 * 	peer EJ for deep copy
 * 	visualgo for animation inspiration
 * 	wikipedia
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JComponent;

/**
 * A bubble sorter that uses the insertion sort algorithm to sort an array.
 * Implemented to create an animation by using threads.
 * @author jessicakrynitsky
 */
public class Insertion {
	private JComponent component;
	private Lock sortStateLock;
	private int testPosition, sortingPosition;
	private int[] a;
	

	public Insertion(int[] anArray, JComponent aComponent) {
		//creates a deep copy of the passed array
		component = aComponent;
		a = new int[50];
		System.arraycopy(anArray, 0, a, 0, anArray.length);
		sortStateLock = new ReentrantLock();
	}
	/**
	 * Sorts the array.
	 * @throws InterruptedException
	 */
	public void sort() 
			throws InterruptedException
	{ 
		for (int i = 1; i < a.length; i++) { 
			sortStateLock.lock();
			try {
				int current = a[i]; 
				testPosition=i;
				// move before all larger elements
				int j = i; 
				while (j > 0 && a[j - 1] > current) { 
					sortingPosition = j;
					a[j] = a[j - 1]; 
					j--; 
					pause(1);
				}
				a[j] = current;
			}
			finally {
				sortStateLock.unlock();
			}
			pause(2);
		}
	}

	/**
	 * draws rectangles for the elements in the array that update as they are sorted.
	 * @param g graphics
	 */
	public void draw(Graphics g) 
	{
		sortStateLock.lock();
		try
		{
			int deltaX = component.getWidth() / a.length;
			for (int i = 0; i < a.length; i++)
			{
				if (i != a.length - 1 && i == testPosition +1) {
					g.setColor((Color.MAGENTA));
				}
				else if (i == sortingPosition) {
					g.setColor(Color.GREEN);
				}
				else if (i <= testPosition) {
					g.setColor(Color.BLUE);
				}
				else {
					g.setColor(Color.WHITE);
				}
				g.drawRect(i*deltaX, 0, 2, a[i] * 5);
				g.fillRect(i*deltaX, 0, 2, a[i] * 5);
			}
		}
		finally {
			sortStateLock.unlock();
		}
	}
	
	/**
	 * Causes the thread to sleep while the component repaints.
	 * @param steps an integer for how long to pause
	 * @throws InterruptedException
	 */
	public void pause(int steps) 
			throws InterruptedException 
	{ 
		component.repaint(); 
		Thread.sleep((long) (steps * 90)); 
	}
}
