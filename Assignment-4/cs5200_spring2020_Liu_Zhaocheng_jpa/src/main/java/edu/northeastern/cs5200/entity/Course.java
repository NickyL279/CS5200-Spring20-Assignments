package edu.northeastern.cs5200.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
	private List<Section> section = new ArrayList<>();

	@ManyToOne
	private Faculty faculty;

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

	public Course() {
	}

	public Course(String title, Faculty faculty) {
		this.title = title;
		this.faculty = faculty;
	}

	public List<Section> getSection() {
		return section;
	}

	public void setSection(List<Section> section) {
		this.section = section;
	}

	public void setSection(Section section) {
		this.section.add(section);
		if (section.getCourse() != this) {
			section.setCourse(this);
		}
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
		if (!faculty.getAuthorOfCourse().contains(this)) {
			faculty.getAuthorOfCourse().add(this);
		}
	}

}
