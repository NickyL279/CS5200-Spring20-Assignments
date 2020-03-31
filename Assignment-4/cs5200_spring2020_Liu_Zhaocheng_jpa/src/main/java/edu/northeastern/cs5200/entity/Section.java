package edu.northeastern.cs5200.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private Integer seats;
	
	@ManyToOne
	private Course course;
	
	@OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
	private List<Enrollment> enrollment = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	
	public Section() {
		
	}
	
	public Section(String title, Integer seats, Course course) {
		this.title = title;
		this.seats = seats;
		this.course = course;
	}

	public List<Enrollment> getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(List<Enrollment> enrollment) {
		this.enrollment = enrollment;
	}
	
	public void setEnrollment(Enrollment enrollment) {
        this.enrollment.add(enrollment);
        if (enrollment.getSection() != this) {
            enrollment.setSection(this);
        }
    }

	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
		if(!course.getSection().contains(this)) {
			course.getSection().add(this);
		}
	}
}
