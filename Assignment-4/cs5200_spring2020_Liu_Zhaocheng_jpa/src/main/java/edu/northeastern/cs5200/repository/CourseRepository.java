package edu.northeastern.cs5200.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import edu.northeastern.cs5200.entity.*;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	@Query(value = "select * from Course where Course.faculty = :faculty", nativeQuery = true)
	public List<Course> findCoursesForAuthor(@Param("faculty") Faculty faculty);
//	@Query("SELECT c FROM Course c Where c.title=:title")
//	public Course findCourseByTitle(@Param("title") String title);
}
