package edu.northeastern.cs5200.repository;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
