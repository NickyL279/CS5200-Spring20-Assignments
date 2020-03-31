package edu.northeastern.cs5200.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Student extends Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer gradYear;
	private Long scholarship;

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
	private List<Enrollment> enrollments = new ArrayList<>();

	public Student() {

	}
	
	public Student(String username, String password, String firstName, String lastName) {
		super();
	}
	public Student(String username, String password, String firstName, String lastName, Integer gradYear, Long scholarship) {
		super(username, password, firstName, lastName);
		this.gradYear = gradYear;
		this.scholarship = scholarship;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public void setEnrollments(Enrollment enrollment) {
		this.enrollments.add(enrollment);
		if (enrollment.getStudent() != this) {
			enrollment.setStudent(this);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGradYear() {
		return gradYear;
	}

	public void setGradYear(Integer gradYear) {
		this.gradYear = gradYear;
	}

	public Long getScholarship() {
		return scholarship;
	}

	public void setScholarship(Long scholarship) {
		this.scholarship = scholarship;
	}
}
