package com.oasis.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.model.ArtDTO;
import com.oasis.model.ArtListDTO;
import com.oasis.model.ArtVO;
import com.oasis.model.ForumArtDTO;
import com.oasis.model.GameVO;
import com.oasis.model.UserVO;
import com.oasis.repository.GameRepository;
import com.oasis.service.ArtService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class ArtController {
	@Autowired
	private ArtService artService;
	@Autowired
	private GameRepository gameRepository;

	// 獲取討論區文章
	@GetMapping("/forum/{gameId}")
	public ResponseEntity<?> getForumArt(@PathVariable String gameId) {
		Integer intGameId = Integer.valueOf(gameId);
		List<ArtVO> artVO = artService.getAllArtByGameId(intGameId);
		List<ForumArtDTO> forumArtDTO = artService.getAllArtsByGameId(artVO);
		if (forumArtDTO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No articles found for the given game ID.");
		}
		return ResponseEntity.ok(forumArtDTO);
	}

	// 獲取首篇文章
	@GetMapping("/art/{artId}")
	public ResponseEntity<?> getArtView(@PathVariable String artId) {
		Integer intArtId = Integer.valueOf(artId);
		ArtDTO artDTO = artService.getFirstArtWithMessage(intArtId);
		 if (artDTO == null) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                             .body("找不到該文章，請檢查文章 ID");
		    }
		return ResponseEntity.ok(artDTO);
	}

	// 獲取回覆文章
	@GetMapping("/reply/{artId}")
	public ResponseEntity<?> getReply(@PathVariable String artId) {
		Integer intArtId = Integer.valueOf(artId);
		List<ArtDTO> artDTO = artService.getReplyArtWithMessage(intArtId);
		return ResponseEntity.ok(artDTO);
	}

	// 新增文章
	@PostMapping("/postArt")
	public ResponseEntity<?> postArt(@RequestBody Map<String, Object> artData, HttpSession session) {
		try {

			UserVO user = (UserVO) session.getAttribute("user");

			if (user == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("使用者未登入");
			}

			ArtVO art = new ArtVO();
			String artTitle = (String) artData.get("artTitle");
			String artContent = (String) artData.get("artContent");
			String gameIdStr = (String) artData.get("gameId");
			Integer gameId = Integer.parseInt(gameIdStr);
			
			art.setArtTitle(artTitle);
			art.setArtContent(artContent);
			Optional<GameVO> optGame = gameRepository.findById(gameId);
			art.setGameVO(optGame.orElse(null));
			art.setUserVO(user);
			art.setArtTimestamp(Timestamp.valueOf(LocalDateTime.now()));
			art.setArtStatus(0);
			art.setArtReply(null);
			Integer artId = artService.CreateArt(art);

			return ResponseEntity.status(HttpStatus.CREATED).body(artId);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("新增文章失敗");
		}
	}
	// 回覆文章
		@PostMapping("/replyArt")
		public ResponseEntity<?> replyArt(@RequestBody Map<String, Object> artData, HttpSession session) {
			try {

				UserVO user = (UserVO) session.getAttribute("user");

				if (user == null) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("使用者未登入");
				}

				ArtVO art = new ArtVO();
				String artTitle = (String) artData.get("artTitle");
				String artContent = (String) artData.get("artContent");
				String gameIdStr = (String) artData.get("gameId");
				Integer gameId = Integer.parseInt(gameIdStr);
				String artIdStr = (String) artData.get("artId");
				Integer artId = Integer.parseInt(artIdStr);
				
				art.setArtTitle(artTitle);
				art.setArtContent(artContent);
				Optional<GameVO> optGame = gameRepository.findById(gameId);
				art.setGameVO(optGame.orElse(null));
				art.setUserVO(user);
				art.setArtTimestamp(Timestamp.valueOf(LocalDateTime.now()));
				art.setArtStatus(0);
				art.setArtReply(artId);
				artService.CreateArt(art);

				return ResponseEntity.status(HttpStatus.CREATED).body(artId);

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("新增文章失敗");
			}
		}
		// 依照使用者獲取文章
		@GetMapping("/getArtList")
		public ResponseEntity<?> getArtList(HttpSession session){
			UserVO user = (UserVO) session.getAttribute("user");

			if (user == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("使用者未登入");
			}
			
			 List<ArtListDTO> artListDTO = artService.getAllArtByUserId(user.getUserId());
			 		
			 if (artListDTO.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No articles found for the given game ID.");
				}
				return ResponseEntity.ok(artListDTO);
			}
		
//		//修改文章
//		@PutMapping("/update")
//		public ResponseEntity<?> updateArt(@RequestBody Map<String, Object> artData){
//			Integer artId = (Integer) artData.get("artId"); 
//			String artContent = (String) artData.get("artContent");
//			Timestamp artTimestamp = Timestamp.valueOf(LocalDateTime.now());
//			 ArtVO artVO = artService.updateArt(artId, artContent, artTimestamp);
//			 return ResponseEntity.ok(artVO);
//			
//		}
}
