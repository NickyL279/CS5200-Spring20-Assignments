package edu.northeastern.cs5200.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entity.*;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {

	@Query(value = "select * from Enrollment where Enrollment.section = :section", nativeQuery = true)
	public List<Enrollment> findStudentsInSection(@Param("section") Section section);

	@Query(value = "select * from Enrollment where Enrollment.student = :student", nativeQuery = true)
	public List<Enrollment> findSectionsForStudent(@Param("student") Student student);

}
