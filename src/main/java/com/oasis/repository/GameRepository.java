package com.oasis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis.model.GameVO;

public interface GameRepository extends JpaRepository<GameVO, Integer> {
	
}
