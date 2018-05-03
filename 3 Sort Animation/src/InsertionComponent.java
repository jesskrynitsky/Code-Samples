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
import java.awt.Graphics;
import javax.swing.JComponent;
/**
 * Creates a component that animates the insertion sort algorithm.
 * @author jessica krynitsky
 *
 */
public class InsertionComponent extends JComponent
{

	private static final long serialVersionUID = 1L;
	private Insertion sorter;

	/**
	 * Constructs the component by creating an insertion sort class.
	 * @param the array to be sorted
	 */
	public InsertionComponent(int[] values)
	{
		sorter = new Insertion(values, this);
	}
	
	/**
	 * Uses the Insertion sorter's draw method to paint the component.
	 */
	public void paintComponent(Graphics g)
	{
		sorter.draw(g);
	}

	/**
	 * starts the animation by running a thread
	 */
	public void startAnimation()
	{
		class AnimationRunnable implements Runnable
		{
			public void run()
			{
				try {
					sorter.sort();
				}
				catch (InterruptedException e) { }
			}
		}

		Runnable r = new AnimationRunnable();
		Thread t = new Thread(r);
		t.start();
	}
}
