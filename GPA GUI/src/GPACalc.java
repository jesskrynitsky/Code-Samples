//Lily Lin
//lml6uf
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;



public class GPACalc extends JFrame implements ActionListener {
	
	private JTextField courseName;
	private JTextField numCredits;
	private JComboBox grade; 
	private double gpa;
	private JFrame frame;
	private JTable table;
	DefaultTableModel model;
	private JTextField removeCourse;
	private ArrayList<Double> gpaSum;
	private ArrayList<Integer> credits;
	private JLabel displayGPA;
	private int totalCredits;
	
	public GPACalc(){
		createAndShowGUI();
		gpaSum = new ArrayList<Double>();
		credits = new ArrayList<Integer>();
		totalCredits = this.totalCredits;
		
	}


	 public void createAndShowGUI() {
	        //Create and set up the window.
		 	frame = new JFrame(getClass().getSimpleName());
		 	frame.setSize(500,7004);
	        //frame.setLocationRelativeTo(null);
	
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //Set up the content pane.
			Double[] grades = {4.0, 3.7, 3.3, 3.0, 2.7, 2.3, 2.0, 1.7, 1.3, 1.0, 0.7, 0.3, 0.0};
			
			//Panel1
			JPanel panel1 = new JPanel();
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
			JLabel infoLabel1 = new JLabel("Course Name:");
			courseName = new JTextField(3);
			panel1.add(infoLabel1);
			panel1.add(courseName);
			
			
			JLabel infoLabel2 = new JLabel("Number of credits:");
			numCredits = new JTextField(3);
			panel1.add(infoLabel2);
			panel1.add(numCredits);
			
			
			JLabel infoLabel3 = new JLabel("Grade:");
			grade = new JComboBox(grades);
			grade.setSelectedItem(null);
	        grade.addActionListener(this);
			panel1.add(infoLabel3);
			panel1.add(grade);
			
			
			//Add button to panel1 
			JButton gpaButton = new JButton("Add Course");
			gpaButton.setActionCommand("add");
			gpaButton.addActionListener(this);
			panel1.add(gpaButton); 
			//table   
			model = new DefaultTableModel(0, 3);
			table = new JTable(model);
			model.addRow(new Object[] {"Class","Credits","Grade"} );
			JLabel label = new JLabel("Courses");
			JPanel panel2 = new JPanel(new BorderLayout());
			panel2.add(label);
			panel2.add(table);
			
	
			JPanel panel3 = new JPanel();
			panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
			displayGPA = new JLabel("GPA: " + gpa);
			JButton calcGPA = new JButton("Calculate GPA");
			calcGPA.setActionCommand("calculate");
			calcGPA.addActionListener(this);
			
			JButton removeButton = new JButton("Remove Course");
			removeButton.setActionCommand("remove");
			removeButton.addActionListener(this);
			JButton refreshButton = new JButton("Refresh");
			refreshButton.setActionCommand("refresh");
			refreshButton.addActionListener(this);
			panel3.add(displayGPA);
			panel3.add(calcGPA);
			panel3.add(removeButton);
			panel3.add(refreshButton);
			

		
			frame.add(panel1, BorderLayout.NORTH);
			frame.add(panel2, BorderLayout.WEST);
			frame.add(panel3, BorderLayout.EAST);
	
			
	        
	        frame.setVisible(true);
	       
	       
	    }
	
	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {     
	               new GPACalc().createAndShowGUI();
	            }
	        });
		
	}
	public void actionPerformed(ActionEvent e) {
	     // add row dynamically into the table    
		if(e.getActionCommand().equals("add")) {
			
			Object[] data = {courseName.getText(), numCredits.getText(), grade.getSelectedItem()};
			model.addRow(data);
			Integer cr = 0;
			try {
				cr = Integer.parseInt(numCredits.getText());
			}catch(NumberFormatException e1) {
				System.out.println("This is not a number");
		          System.out.println(e1.getMessage());
			}
//			if((Double)grade.getSelectedItem() != null) {
				gpaSum.add((Double) grade.getSelectedItem());
				credits.add(cr);
				totalCredits += Integer.parseInt(numCredits.getText());
//			}
			
			
		}
		if(e.getActionCommand().equals("calculate")) {
			
			double gradePoints = 0.0;
			
			for(int i = 0; i <gpaSum.size(); i++) {
				if(gpaSum.get(i)!= null) {
					double gpaDouble = Double.parseDouble(gpaSum.get(i).toString());
					gradePoints += gpaDouble*(credits.get(i));
				}
			}
			gpa = gradePoints/totalCredits;
			DecimalFormat df = new DecimalFormat("#.##");
			double p = Double.parseDouble(df.format(gpa));
			
			displayGPA.setText("GPA: " + p );
		}
		if(e.getActionCommand().equals("remove")) {
			if(table.getSelectedRow() != 0 && table.getSelectedRow()>0) {
				
				displayGPA.setText("GPA:" +"");
				//model.removeRow(table.getSelectedRow());
				totalCredits -= credits.get(table.getSelectedRow()-1);
				gpaSum.remove(table.getSelectedRow()-1);
				credits.remove(table.getSelectedRow()-1);
				model.removeRow(table.getSelectedRow());

			}
			
		}
		if(e.getActionCommand().equals("refresh")) {
			if(table.getSelectedRow() != 0 && table.getSelectedRow()>0) {
				model = new DefaultTableModel(0, 3);
				table = new JTable(model);
				model.addRow(new Object[] {"Class","Credits","Grade"} );

			}
			
		}
		}	
	
	}
