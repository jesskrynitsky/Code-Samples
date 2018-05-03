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
 * A bubble sorter that uses the selection sort algorithm to sort an array.
 * Implemented to create an animation by using threads.
 * @author jessicakrynitsky
 */
public class Selection 
{
	private int[] a;
	private int markedPosition, alreadySorted, currentMin;
	private JComponent component;
	private Lock sortStateLock;

	/**
	 * @param anArray 
	 * @param aComponent
	 */
	public Selection(int[] anArray, JComponent aComponent) 
	{
		// creates a deep copy of the passed array
		a = new int[50];
		System.arraycopy(anArray, 0, a, 0, anArray.length);
		markedPosition = -1;
		alreadySorted = -1;
		component = aComponent;
		sortStateLock = new ReentrantLock();
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
		Thread.sleep((long) (steps * 30)); 
	}

	/**
	 * Sorts the array.
	 * @throws InterruptedException
	 */
	public void sort() 
			throws InterruptedException
	{
		for (int i = 0; i < a.length - 1; i++) {
			int minPos = minimumPosition(i);
			sortStateLock.lock();
			try {
				ArrayUtil.swap(a, minPos, i);
				alreadySorted = i;
				currentMin = 0;
			}
			finally {
				sortStateLock.unlock();
			}
			pause(2);
		}
	}

	/**
	 * Finds the index of the minimum value in a portion of the array.
	 * @param start index to start looking at the array
	 * @return index of min position
	 * @throws InterruptedException
	 */
	private int minimumPosition(int start) 
			throws InterruptedException
	{
		int minPos = start;
		for (int i = start + 1; i < a.length; i++) {
			sortStateLock.lock();
			try {
				if (a[i] < a[minPos]) {
					minPos = i;
					currentMin = i;
				}
				markedPosition = i;
			}
			finally {
				sortStateLock.unlock();
			}
			pause(2);
		}
		return minPos;
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
				if (i != a.length - 1 && i == markedPosition) {
					g.setColor((Color.MAGENTA));
				}
				else if (i == currentMin) {
					g.setColor(Color.GREEN);
				}
				else if (i <= alreadySorted) {
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
}
