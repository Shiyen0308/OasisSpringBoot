package com.oasis.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.model.MessageDTO;
import com.oasis.model.MessageVO;
import com.oasis.model.UserVO;
import com.oasis.service.MessageService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class MessageController {
	@Autowired
	private MessageService messageService;

	@PostMapping("/message")
	public ResponseEntity<?> addMessage(@RequestBody Map<String, Object> messageData, HttpSession session) {
		try {
			
			UserVO user = (UserVO)session.getAttribute("user"); 

			if (user == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("使用者未登入");
			}
				
			

			MessageVO message = new MessageVO();
			String messageContent = (String) messageData.get("messageContent");
			String artIdStr = (String) messageData.get("artId");
		    Integer artId = Integer.parseInt(artIdStr); 
		    
		    
	        message.setMessageContent(messageContent);
	        message.setMessageArtId(artId);	        
	        message.setUserVO(user);
	        message.setMessageTimestamp(Timestamp.valueOf(LocalDateTime.now()));
	       
	        
			Integer messageId = messageService.CreateArt(message);
			
			MessageDTO messageDTO = messageService.getMessage(messageId);
			

			return ResponseEntity.status(HttpStatus.CREATED).body(messageDTO);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("新增留言失敗");
		}
	}

}
