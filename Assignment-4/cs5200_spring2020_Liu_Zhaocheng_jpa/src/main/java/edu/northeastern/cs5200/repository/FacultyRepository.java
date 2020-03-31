package edu.northeastern.cs5200.repository;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.entity.Faculty;

public interface FacultyRepository extends CrudRepository<Faculty, Integer> {
//	@Query("SELECT p FROM Person p WHERE p.username=:username ")
//	public Faculty findFacultyByUsername(@Param("username") String username);
}
