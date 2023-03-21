package application;

public class courseData {
	private String Course;
	private String Decription;
	private String Degree;
	
	public courseData(String Course, String Decription, String Degree) {
		this.Course = Course;
		this.Decription = Decription;
		this.Degree = Degree;
	}

	public String getCourse() {
		return Course;
	}

	public String getDecription() {
		return Decription;
	}

	public String getDegree() {
		return Degree;
	}
}
