import java.awt.Graphics;
import javax.swing.JComponent;
/**
 * Creates a component that animates the bubble sort algorithm.
 * @author jessica krynitsky
 *
 */
public class BubbleComponent extends JComponent
{

	private static final long serialVersionUID = 1L;
	private Bubble sorter;

	/**
	 * Constructs the component by creating a bubble sort class.
	 * @param the array to be sorted
	 */
	public BubbleComponent(int[] values) {
		sorter = new Bubble(values, this);
	}
	
	/**
	 * Uses the Bubble sorter's draw method to paint the component.
	 */
	public void paintComponent(Graphics g) {
		sorter.draw(g);
	}

	/**
	 * starts the animation by running a thread
	 */
	public void startAnimation() {
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

