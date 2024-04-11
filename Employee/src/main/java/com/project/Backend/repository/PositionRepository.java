package com.project.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Backend.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{
	
	Position findBytitle(String title);
}
