package application;

import java.sql.Date;

public class studentData {
	
	private Integer IDStudent;
	private String FirstName;
	private String LastName;
	private String gender;
	private Date birthDay;
	private String course;
	private String statusStudent;
	private String imageStudent;
	private Float midScore;
	private Float finalScore;
	private Float score;
	private String rank;
	

	public studentData(Integer IDStudent, String course, Float midScore, Float finalScore, Float score, String rank) {
		this.IDStudent = IDStudent;
		this.course = course;
		this.midScore = midScore;
		this.finalScore = finalScore;
		this.score = score;
		this.rank = rank;
	}

	public studentData(Integer IDStudent, String FirstName, String LastName, String gender, Date birthDay,
			String course, String statusStudent, String imageStudent) {
		this.IDStudent = IDStudent;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.course = course;
		this.statusStudent = statusStudent;
		this.imageStudent = imageStudent;
	}
	

	public Float getMidScore() {
		return midScore;
	}

	public Float getFinalScore() {
		return finalScore;
	}

	public Float getScore() {
		return score;
	}

	public String getRank() {
		return rank;
	}

	public Integer getIDStudent() {
		return IDStudent;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public String getGender() {
		return gender;
	}
	
	public Date getBirthDay() {
		return birthDay;
	}
	
	public String getCourse() {
		return course;
	}
	
	public String getStatusStudent() {
		return statusStudent;
	}

	public String getImageStudent() {
		return imageStudent;
	}

}
