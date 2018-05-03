/*
 * Jessica Krynitsky
 * jmk8vr
 * resources: Big Java, StackOverflow, Java API, Derrick Stone's in class examples
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

public class GPACalculator extends JFrame implements ActionListener{
	
	//adding a course
	JPanel panel;
	private JLabel creditLabel;
	private JLabel gradeLabel;
	private JLabel nameLabel;
	private JTextField creditText;
	private JComboBox<String> gradeBox;
	private JTextField nameText;
	private JButton addButton;
	private Map<String, Double> gradeMap;
	
	//table of current courses
	private JTable table;
	DefaultTableModel model;
	private ArrayList<Integer> creditList;
	private ArrayList<Double> gradeList;
	
	private final DecimalFormat DF = new DecimalFormat("#.#");
	// current GPA
	private double currentGPA;
	private JButton cGPA;
	private JLabel cGPALabel;
	private JTextField target;
	private double targetGPA;
	private double requiredGPA;
	private JLabel req;
	private JLabel reqMessage;
	private JLabel error;
	
	private static final int FRAME_WIDTH = 600; 
	private static final int FRAME_HEIGHT = 450;

	/**
	 * Constructs and opens a GPA calculator of a set size.
	 */
	public GPACalculator() {
		creditList = new ArrayList<>();
		gradeList = new ArrayList<>();
		buildFrame();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Adds elements to the frame in the form of nested panels containing buttons, labels, 
	 * text fields, a dropdown menu (combobox) and a table.
	 */
	public void buildFrame() {
	// create one main panel
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
	// adding a course
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(3,2));
		nameLabel = new JLabel("Class name (optional):");
		nameText = new JTextField(10);
		// assume user will enter grades in letter format, and assume the following grade transformations.
		gradeLabel = new JLabel("Grade received/expected (optional):");
		String[] choices = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
		Double[] values =  {4.0,  3.7,  3.3, 3.0,  2.7,  2.3, 2.0,  1.7,  1.3, 1.0,  0.7, 0.0};
		gradeMap = new HashMap<>();
		for (int i = 0; i < choices.length; i++) {
		    gradeMap.put(choices[i], values[i]);
		}
		gradeBox = new JComboBox<String>(choices);
		gradeBox.setSelectedItem(null);
		creditLabel = new JLabel("Credit hours:");
		creditText = new JTextField(4);
		addButton = new JButton("Add Course");
		addButton.setActionCommand("add");
		addButton.addActionListener(this);
		panel2.add(nameLabel);
		panel2.add(nameText);
		panel2.add(gradeLabel);
		panel2.add(gradeBox);
		panel2.add(creditLabel);
		panel2.add(creditText);
		panel1.add(panel2);
		panel1.add(addButton);
		panel.add(panel1, BorderLayout.NORTH);

	// table of current Courses
		String[] columnHeaders = {"Name", "Grade", "Credits"};
		model = new DefaultTableModel(columnHeaders, 0);
		model.addRow(columnHeaders);
		table = new JTable();
		table.setModel(model);
		table.setBounds(670, 25, 500, 500);
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		JLabel tableLabel = new JLabel("Your Courses");
		panel3.add(table, BorderLayout.CENTER);
		panel3.add(tableLabel, BorderLayout.NORTH);
		panel.add(panel3, BorderLayout.WEST);
		
	// display current GPA
		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(5,1));
		cGPALabel = new JLabel("GPA:");
		panel4.add(cGPALabel);
		
	// add 15 credits
		JButton add5 = new JButton("Add 15 New Credits");
		add5.addActionListener(this);
		add5.setActionCommand("add5");
		panel4.add(add5);
		
	// remove a single course
		JButton remove = new JButton("Remove Course");
		remove.setActionCommand("remove");
		remove.addActionListener(this);
		panel4.add(remove);
		
	// clear all courses
		JButton reset = new JButton("Clear all");
		reset.addActionListener(this);
		reset.setActionCommand("reset");
		panel4.add(reset);

	// add an error message if the user does not input an integer for credit hours
		error = new JLabel("");
		error.setForeground(Color.RED.darker());
		panel4.add(error);
		
	// add a target GPA
		JLabel targetLabel = new JLabel("Enter Target GPA:");
		target = new JTextField(4);
		JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayout(5,1));
		JPanel panel5a = new JPanel();
		panel5a.setLayout(new FlowLayout());
		panel5a.add(targetLabel);
		panel5a.add(target);
		panel5.add(panel5a);

		
	// calculate required GPA
		JButton required = new JButton("Calculate Required GPA");
		required.addActionListener(this);
		required.setActionCommand("required");
		panel5.add(required);
		req = new JLabel("<html> Average GPA required to reach <BR> your target:</html>");
		reqMessage = new JLabel("");
		reqMessage.setForeground(Color.BLUE.darker());
		panel5.add(req);
		panel5.add(reqMessage);
		
	// add everything to main panel
		panel.add(panel4, BorderLayout.CENTER);
		panel.add(panel5, BorderLayout.EAST);
		panel.setBorder(BorderFactory.createTitledBorder("GPA CALCULATOR"));
		add(panel);
	}
	
	/**
	 * Updates the GPA instance variable from the current course list information.
	 * Is only used by other methods in the GPACalculator class.
	 * Assume GPA will be calculated only from classes that have been input with a grade. 
	 */
	private void updateGPA() {
		double gradePoints = 0.0;
		int usedCredits = 0;
		for (int i = 0; i < creditList.size(); i++) {
			if (gradeList.get(i) != null) {
			 gradePoints += gradeList.get(i) * creditList.get(i);
			 usedCredits+= creditList.get(i);
			}
		}
		currentGPA = Double.parseDouble(DF.format(gradePoints/usedCredits));
		cGPALabel.setText("GPA:" + Double.toString(currentGPA));
	}

	/**
	 * Implements the ActionListener interface by handling different actions (i.e., different buttons pressed).
	 * Only one actionPerformed method, which works for every action performed, and does something different based on the actionCommand
	 * (set to something different for each button). In my opinion, this is a succinct way to do this, however the runtime speed may be faster 
	 * if different ActionEvents are implemented.
	 */
	public void actionPerformed(ActionEvent e) {
		
	// Add Course Button Clicked
		if (e.getActionCommand().equals("add")) {
			// assume user has to enter a number of credit hours to add a course.
			// if they do not, the compiler catches an exception, and the GUI prompts the user to enter a number.
			Double grade = (Double) gradeMap.get(gradeBox.getSelectedItem());
			try {
				Integer credit = Integer.parseInt(creditText.getText());
				
				// Only add to the table if there are valid credit hours.
				Object[] data1 = {nameText.getText(), grade, Integer.toString(credit)};
				model.addRow(data1);
				
				// Add grades and credit hours to lists for convenient GPA calculations.
				// Assume course names are only used for display, so do not keep track of them.
				gradeList.add(grade);
				creditList.add(credit);
				
				// Assume automatically updates GPA whenever a course is added
				updateGPA();
				
			}catch(NumberFormatException e1) {
		          if (grade != null) {
			        	  System.out.println("Must enter an integer number of credits.");
				          System.out.println(e1.getMessage());
			        	  error.setText("<html> Please enter an integer <BR> number of credit hours.</html>");
		          }
			}

			
			// Clear texts once button is clicked. Assume this makes for a better interface.
			nameText.setText("");
			gradeBox.setSelectedItem(null);
			creditText.setText("");				
		}
		
	// Calculate current GPA
		// Initially had a button to calculate the GPA,
		// but assumed a better interface would display the GPA automatically.
//		if (e.getActionCommand().equals("current")) {
//			updateGPA();
//		}
		
	// Remove a single course
		if (e.getActionCommand().equals("remove")) {
			// assume the user clicks on a course in the table to highlight it, and then clicks the remove button.
			if (table.getSelectedRow() != 0 && table.getSelectedRow() > 0) {
				// update inner list information
				creditList.remove(table.getSelectedRow() -1);
				gradeList.remove(table.getSelectedRow() -1);
				// remove course from table
				model.removeRow(table.getSelectedRow());
				// update the GPA
				updateGPA();
			}
		}
		
	// Clear all courses / Reset
		// assume there will be courses in the list if they click clear all
		if (e.getActionCommand().equals("reset")) {
			int rowCount = model.getRowCount();
			if (rowCount > 1) {
				for (int i=1; i<rowCount; i++) {
					model.removeRow(i);
				}
				creditList.clear();
				gradeList.clear();
				updateGPA();
			}
		}
		
	// Add 5 empty courses
		// assume that you can only calculate GPA and required GPA if you have at least one course with a grade
		if (e.getActionCommand().equals("add5")) {
			Object[] data1 = {null, null, "3"};
			for (int i=0; i<4; i++) {
				gradeList.add(null);
				creditList.add((Integer) 3);
				model.addRow(data1);
			}
		}
		
	// Calculate Required GPA
		// assume the required GPA is the average GPA needed for all credit hours without a grade,
		// as opposed to a required GPA for each class.
		if (e.getActionCommand().equals("required")) {
			targetGPA = Double.parseDouble(target.getText());
			double gradePoints = 0.0;
			int emptyCredits = 0;
			int usedCredits = 0;
			for (int i = 0; i < creditList.size(); i++) {
				if (gradeList.get(i) != null) {
					gradePoints += gradeList.get(i) * creditList.get(i);
					usedCredits += creditList.get(i); 
				}
				else if (gradeList.get(i) == null) {
					emptyCredits += creditList.get(i);
				}
			}
			requiredGPA = ((targetGPA * (usedCredits + emptyCredits )) - gradePoints) / emptyCredits;
			req.setText("<html> Average GPA required to reach <BR>your target:" + DF.format(requiredGPA) +"</html>");
			if (requiredGPA > 4.0) {
				reqMessage.setText("<html>To reach your target GPA, try <BR>adding more classes and <BR>recalculating.</html>");
			}
			if (requiredGPA < 2.0) {
				reqMessage.setText("<html>Not bad! You could take <BR>fewer classes if you wish, <BR> or aim for a higher GPA.</html>");
			}
		}
	}

	/**
	 * main method creates a new instance of GPACalculator.
	 */
	public static void main(String[] args) {
		new GPACalculator();
	}
}
