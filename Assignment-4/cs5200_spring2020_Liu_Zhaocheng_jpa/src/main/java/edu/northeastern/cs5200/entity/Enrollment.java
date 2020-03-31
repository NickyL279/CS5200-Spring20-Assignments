package edu.northeastern.cs5200.entity;

import javax.persistence.*;

@Entity
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer grade;
	private String feedback;
	@ManyToOne()
	private Section section;
	@ManyToOne()
	private Student student;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	public Enrollment() {

	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
		if (!student.getEnrollments().contains(this)) {
			student.getEnrollments().add(this);
		}
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
		if (!section.getEnrollment().contains(this)) {
			section.getEnrollment().add(this);
		}
	}
}
