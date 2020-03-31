package edu.northeastern.cs5200.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entity.*;

public interface SectionRepository extends CrudRepository<Section, Integer> {
	@Query(value = "select * from Section where Section.course = :course", nativeQuery = true)
	public List<Section> findSectionForCourse(@Param("course") Course course);
}
