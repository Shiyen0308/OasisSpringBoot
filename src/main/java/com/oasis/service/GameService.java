package com.oasis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasis.model.GameVO;
import com.oasis.repository.GameRepository;

@Service
public class GameService {
	@Autowired
	private GameRepository gameRepository;

	public List<GameVO> getAllGame() {
		List<GameVO> games = gameRepository.findAll();
		return games;
	}
}
