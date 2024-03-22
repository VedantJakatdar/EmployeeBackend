package com.project.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Backend.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{

//	Position findByTitle(String title);

	Position findBytitle(String title);

}
