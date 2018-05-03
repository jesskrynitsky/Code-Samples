import java.awt.Graphics;
import javax.swing.JComponent;
/**
 * Creates a component that animates the selection sort algorithm.
 * @author jessica krynitsky
 *
 */
public class SelectionSortComponent extends JComponent
{

	private static final long serialVersionUID = 1L;
	private Selection sorter;
	
	/**
	 * @param the array to be sorted
	 */
	public SelectionSortComponent(int[] values)
	{
		sorter = new Selection(values, this);
	}
	
	/**
	 * Uses the Selection sorter's draw method to paint the component.
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
