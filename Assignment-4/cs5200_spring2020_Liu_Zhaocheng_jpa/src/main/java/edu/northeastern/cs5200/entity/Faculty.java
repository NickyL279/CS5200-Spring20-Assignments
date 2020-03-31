package edu.northeastern.cs5200.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Faculty extends Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
	private List<Course> authorCourse = new ArrayList<>();
	
	private String office;
	private Boolean tenured;
	
	public Faculty() {
		
	}
	
	public Faculty(String username, String password, String firstName, String lastName, String office, boolean tenured) {
		super(username, password, firstName, lastName);
		this.office = office;
		this.tenured = tenured;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public Boolean getTenured() {
		return tenured;
	}
	public void setTenured(Boolean tenured) {
		this.tenured = tenured;
	}
	
	public List<Course> getAuthorOfCourse() {
		return authorCourse;
	}
	public void setAuthorOfCourse(List<Course> authorCourse) {
		this.authorCourse = authorCourse;
	}
	
	public void taughtCourse(Course course) {
		authorCourse.add(course);
		if(course.getFaculty() != this) {
			course.setFaculty(this);
		}
	}
	
}
