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
import java.util.Random;

public class ArrayUtil {
	
	private static Random generator = new Random();
	
	public static int[] randomIntArray(int length, int n)
	{
		int[] a = new int[length];
		for (int i = 0; i < a.length; i++) {
			a[i] = generator.nextInt(n);
		}
		return a;
	}
/**
 * Swaps two elements in an array
 * @param a array list
 * @param i first index to swap
 * @param j second index to swap
 */
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
