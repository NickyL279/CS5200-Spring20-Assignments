package edu.northeastern.cs5200.dao;

import edu.northeastern.cs5200.entity.*;
import edu.northeastern.cs5200.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UniversityDao {
	@Autowired
	FacultyRepository facultyRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	CourseRepository courseRepository;

	public void truncateDatabase() {
		enrollmentRepository.deleteAll();
		sectionRepository.deleteAll();
		courseRepository.deleteAll();
		facultyRepository.deleteAll();
		studentRepository.deleteAll();
		personRepository.deleteAll();
	}

	public Faculty createFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}

	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	public Section createSection(Section section) {
		return sectionRepository.save(section);
	}

	public Course addSectionToCourse(Section section, Course course) {
		// section.setCourse(course);
		course.setSection(section);
		sectionRepository.save(section);
		return courseRepository.save(course);
	}

	public Course setAuthorForCourse(Faculty faculty, Course course) {
		// faculty.taughtCourse(course);
		course.setFaculty(faculty);
		facultyRepository.save(faculty);
		return courseRepository.save(course);
	}

	public Boolean enrollStudentInSection(Student student, Section section) {
		if (section.getSeats() == 0) {
			return false;
		}
		Enrollment enrollment = new Enrollment();
		enrollment.setSection(section);
		enrollment.setStudent(student);
		enrollmentRepository.save(enrollment);
		section.setSeats(section.getSeats() - 1);
		sectionRepository.save(section);
		return true;
	}

	public List<Person> findAllUsers() {
		return (List<Person>) personRepository.findAll();
	}

	public List<Faculty> findAllFaculty() {
		return (List<Faculty>) facultyRepository.findAll();
	}

	public List<Student> findAllStudents() {
		return (List<Student>) studentRepository.findAll();
	}

	public List<Course> findAllCourses() {
		return (List<Course>) courseRepository.findAll();
	}

	public List<Section> findAllSections() {
		return (List<Section>) sectionRepository.findAll();
	}

	public List<Course> findCoursesForAuthor(Faculty faculty) {
		return (List<Course>) faculty.getAuthorOfCourse();
	}

	public List<Section> findSectionForCourse(Course course) {
		return (List<Section>) course.getSection();
	}

	public List<Student> findStudentsInSection(Section section) {
		List<Enrollment> enrollments = section.getEnrollment();

		List<Student> students = new ArrayList<Student>();
		for (Enrollment en : enrollments) {
			students.add(en.getStudent());
		}
		return students;
	}

	public List<Section> findSectionsForStudent(Student student) {
		List<Enrollment> enrollments = student.getEnrollments();
		List<Section> sections = new ArrayList<Section>();
		for (Enrollment enrollment : enrollments) {
			sections.add(enrollment.getSection());
		}
		return sections;
	}

}
