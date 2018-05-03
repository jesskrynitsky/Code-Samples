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
import javax.swing.JFrame;

public class GPACalulatorViewer {
	public static void main(String[] args) {
        //Create and set up the window.
        GPACalculator frame = new GPACalculator();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.createComponents();
        frame.setVisible(true);
	}
}
