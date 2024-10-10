package com.oasis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.model.GameVO;
import com.oasis.service.GameService;

@RestController
@RequestMapping("/api")
public class GameController {
	@Autowired
	private GameService gameService;
	
	@GetMapping("/games")
	public ResponseEntity<?> getAllGame(){
		List<GameVO> games = gameService.getAllGame();
		return ResponseEntity.ok(games);
	}
}
