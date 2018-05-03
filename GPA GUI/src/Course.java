
public class Course {
	private int credits;
	private double grade;
	private String name;
	
	public Course() {
		credits = 3;
	}
	
	public Course(int credits) {
		this.credits = credits;
	}
	
	public Course(int credits, String name, double grade) {
		this.credits = credits;
		this.grade = grade;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public double getGrade() {
		return grade;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + "credits:" + credits + ", grade:" + grade;
	}
	
	
}
