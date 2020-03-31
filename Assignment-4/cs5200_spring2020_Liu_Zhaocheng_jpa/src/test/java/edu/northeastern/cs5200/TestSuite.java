package edu.northeastern.cs5200;

import edu.northeastern.cs5200.dao.UniversityDao;
import edu.northeastern.cs5200.entity.*;
import edu.northeastern.cs5200.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSuite {

	@Autowired
	UniversityDao universityDao;

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

	@Test
	public void TestSuites() {
		universityDao.truncateDatabase();

		Faculty alan = new Faculty("alan", "password", "Alan", "Turin", "123A", true);
		universityDao.createFaculty(alan);
		Faculty ada = new Faculty("ada", "password", "Ada", "Lovelace", "123B", true);
		universityDao.createFaculty(ada);

		Student alice = new Student("alice", "password", "Alice", "Wonderland", 2020, (long) 12000);
		universityDao.createStudent(alice);
		Student bob = new Student("bob", "password", "Bob", "Hope", 2021, (long) 23000);
		universityDao.createStudent(bob);
		Student charlie = new Student("charlie", "password", "Charlie", "Brown", 2019, (long) 21000);
		universityDao.createStudent(charlie);
		Student dan = new Student("dan", "password", "Dan", "Craig", 2019, (long) 0);
		universityDao.createStudent(dan);
		Student edward = new Student("edward", "password", "Edward", "Scissorhands", 2022, (long) 11000);
		universityDao.createStudent(edward);
		Student frank = new Student("frank", "password", "Fran", "Herbert", 2018, (long) 0);
		universityDao.createStudent(frank);
		Student gregory = new Student("gregory", "password", "Gregory", "Peck", 2023, (long) 10000);
		universityDao.createStudent(gregory);

		Course cs1234 = new Course("CS1234", alan);
		universityDao.createCourse(cs1234);
		Course cs2345 = new Course("CS2345", alan);
		universityDao.createCourse(cs2345);
		Course cs3456 = new Course("CS3456", ada);
		universityDao.createCourse(cs3456);
		Course cs4567 = new Course("CS4567", ada);
		universityDao.createCourse(cs4567);

		Section sec4321 = new Section("SEC4321", 50, cs1234);
		universityDao.createSection(sec4321);
		Section sec5432 = new Section("SEC5432", 50, cs1234);
		universityDao.createSection(sec5432);
		Section sec6543 = new Section("SEC6543", 50, cs2345);
		universityDao.createSection(sec6543);
		Section sec7654 = new Section("SEC7654", 50, cs3456);
		universityDao.createSection(sec7654);

		universityDao.enrollStudentInSection(alice, sec4321);
		universityDao.enrollStudentInSection(alice, sec5432);
		universityDao.enrollStudentInSection(bob, sec5432);
		universityDao.enrollStudentInSection(charlie, sec6543);
	}

	@Test
	public void validateUsers() {
		List<Person> users = universityDao.findAllUsers();
		// int numberOfUsers = users.size();
		System.out.println("The total number of users is" + users.size());
	}

	@Test
	public void validateFaculties() {
		List<Faculty> faculties = universityDao.findAllFaculty();
		System.out.println("The total number of faculties is" + faculties.size());
	}

	@Test
	public void validateStudents() {
		List<Student> students = universityDao.findAllStudents();
		System.out.println("The total number of students is" + students.size());
	}

	@Test
	public void validateCourses() {
		List<Course> courses = universityDao.findAllCourses();
		System.out.println("The total number of courses is" + courses.size());
	}

	@Test
	public void validateSections() {
		List<Section> sections = universityDao.findAllSections();
		System.out.println("The total number of sections is" + sections.size());
	}

	@Test
	public void validateCourseAuthorship() {
		List<Faculty> faculties = universityDao.findAllFaculty();
		for (Faculty faculty : faculties) {
			List<Course> courses = universityDao.findCoursesForAuthor(faculty);
			System.out.println(
					"The total number of courses authored by " + faculty.getFirstName() + "is " + courses.size());
		}
	}

	@Test
	public void validateSectionPerCourse() {
		List<Course> courses = universityDao.findAllCourses();
		for (Course course : courses) {
			List<Section> sections = universityDao.findSectionForCourse(course);
			System.out.println("The total number of sections of course " + course.getTitle() + "is " + sections.size());
		}
	}

	@Test
	public void validateSectionEnrollments() {
		List<Section> sections = universityDao.findAllSections();
		for (Section section : sections) {
			List<Student> students = universityDao.findStudentsInSection(section);
			System.out
					.println("The total number of students in section " + section.getTitle() + "is " + students.size());
		}
	}

	@Test
	public void validateStudentEnrollments() {
		List<Student> students = universityDao.findAllStudents();
		for (Student student : students) {
			List<Section> sections = universityDao.findSectionsForStudent(student);
			System.out.println(
					"The total number of sections for student " + student.getFirstName() + "is " + sections.size());
		}
	}

	@Test
	public void validateSectionSeats() {
		List<Section> sections = universityDao.findAllSections();
		for (Section section : sections) {
			System.out.println("Section " + section.getTitle() + "has " + section.getSeats() + "seats");
		}
	}

}
