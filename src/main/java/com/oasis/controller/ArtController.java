package com.oasis.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		ArtDTO artDTO = artService.getFirstArtWithMessage(Integer.valueOf(artId));
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
	public ResponseEntity<?> postArt(@RequestBody Map<String, String> artData, HttpSession session) {
		try {

			UserVO user = (UserVO) session.getAttribute("user");

			if (user == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("使用者未登入");
			}
			String artTitle = artData.get("artTitle");
			String artContent =  artData.get("artContent");
			Integer gameId = Integer.valueOf(artData.get("gameId"));
			Optional<GameVO> optGame = gameRepository.findById(gameId);
			GameVO gameVO = (GameVO)optGame.orElse(null);
			ArtVO art = ArtVO.builder().artTitle(artTitle).artContent(artContent)
						.artReply(null).artStatus(0)
						.artTimestamp(Timestamp.valueOf(LocalDateTime.now()))
						.gameVO(gameVO).userVO(user).build();
			
			
			
			Integer artId = artService.CreateArt(art);

			return ResponseEntity.status(HttpStatus.CREATED).body(artId);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("新增文章失敗");
		}
	}
	// 回覆文章
		@PostMapping("/replyArt")
		public ResponseEntity<?> replyArt(@RequestBody Map<String, String> artData, HttpSession session) {
			try {

				UserVO user = (UserVO) session.getAttribute("user");

				if (user == null) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("使用者未登入");
				}
				String artTitle = artData.get("artTitle");
				String artContent = artData.get("artContent");
				Integer gameId = Integer.valueOf(artData.get("gameId"));
				Integer artId = Integer.valueOf(artData.get("artId"));
				Optional<GameVO> optGame = gameRepository.findById(gameId);
				GameVO gameVO = (GameVO)optGame.orElse(null);
				ArtVO art = ArtVO.builder().artTitle(artTitle).artContent(artContent)
							.artStatus(0).gameVO(gameVO).artReply(artId)
							.artTimestamp(Timestamp.valueOf(LocalDateTime.now()))
							.userVO(user).build();
				
				
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
		
		//修改文章
		@PutMapping("/update")
		public ResponseEntity<?> updateArt(@RequestBody Map<String, String> artData){
			Integer artId = Integer.valueOf(artData.get("artId")); 
			String artContent = artData.get("artContent");
			Timestamp artTimestamp = Timestamp.valueOf(LocalDateTime.now());
			 if (artService.updateArt(artId, artContent, artTimestamp)) {
				 return ResponseEntity.ok().build();
			 }else {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("文章不存在或更新失敗"); 
			 }
		}
		//刪除文章
		@PutMapping("/delete")
		public ResponseEntity<?> deleteArt(@RequestBody Map<String, String> artData){
			Integer artId = Integer.valueOf(artData.get("artId")); 
			Timestamp artTimestamp = Timestamp.valueOf(LocalDateTime.now());
			 if (artService.deleteArt(artId, artTimestamp)) {
				 return ResponseEntity.ok(true);
			 }else {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("文章不存在或更新失敗"); 
			 }
		}
//		// 搜尋文章
//		@GetMapping("/search")
//		public ResponseEntity<Page<ArtVO>> searchPosts(@RequestParam String keyword,
//                @RequestParam int page,
//                @RequestParam int size) {
//				Page<ArtVO> posts = artService.searchPosts(keyword, PageRequest.of(page - 1, size));
//					return ResponseEntity.ok(posts); // 回傳 200 OK 和結果
//			}
}
