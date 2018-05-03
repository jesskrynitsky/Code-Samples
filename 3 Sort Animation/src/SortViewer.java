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
import java.awt.GridLayout;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Creates an animation window that displays the running
 * of three different algorithms simultaneously.
 * @author jessica krynitsky
 */
public class SortViewer 
{
	public static void main(String[] args)
	{ 
		// create an array of integers 1-50 in random order
		int[] values = new int[50];
		int j = 1;
		for (int i = 0; i < values.length; i++) {
			values[i] = j;
			j++;
		}
		shuffleArray(values);
		
		// create frame
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(2,3));
		final int FRAME_WIDTH = 1000;
		final int FRAME_HEIGHT = 500;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		
		// create each algorithm animation component
		final SelectionSortComponent sComponent = new SelectionSortComponent(values);
		final InsertionComponent iComponent = new InsertionComponent(values);
		final BubbleComponent bComponent = new BubbleComponent(values);
		
		// create the titles
		JLabel iTitle = new JLabel("INSERTION SORT");
		JLabel bTitle = new JLabel("BUBBLE SORT");
		JLabel sTitle = new JLabel("SELECTION SORT");
		sTitle.setForeground(Color.WHITE);
		iTitle.setForeground(Color.WHITE);
		bTitle.setForeground(Color.WHITE);
		
		// add everything to the frame and show it
		frame.add(sTitle);
		frame.add(iTitle);
		frame.add(bTitle);
		frame.add(sComponent); 
		frame.add(iComponent);
		frame.add(bComponent);
		frame.setVisible(true);
		
		//start the animation
		sComponent.startAnimation();
		iComponent.startAnimation();
		bComponent.startAnimation();
	} 
	
	/**
	 * shuffles the elements of an array
	 * @param ar array to be shuffled
	 */
	static void shuffleArray(int[] ar)
	{
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	}
}
