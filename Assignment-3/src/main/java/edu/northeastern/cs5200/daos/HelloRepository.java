package edu.northeastern.cs5200.daos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository
	extends JpaRepository<HelloObject, Integer> {
}
