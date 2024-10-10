package com.oasis.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oasis.model.ArtVO;
import com.oasis.model.ForumArtDTO;
import com.oasis.model.MessageDTO;
import com.oasis.model.MessageVO;
import com.oasis.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	
	// 新增留言
		@Transactional
		public Integer CreateArt(MessageVO messageVO) {
			try {
				MessageVO savedMessage = messageRepository.save(messageVO);
				return savedMessage.getMessageId();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		// 依留言ID 查詢
		public MessageDTO getMessage(Integer messageId) {
			Optional<MessageVO> optMessage = messageRepository.findById(messageId);
			 if (optMessage.isPresent()) {
			        MessageVO messageVO = optMessage.get();
			        // 轉換為 MessageDTO
			        MessageDTO messageDTO = new MessageDTO(
			            messageVO.getMessageContent(),
			            messageVO.getMessageTimestamp(),
			            messageVO.getUserVO().getUserNickname()
			        );
			        return messageDTO;
			    } else {
			        
			        return null;  
			    }
			
		}
		
		// 依文章ID 查詢留言
		public List<MessageVO> getAllMessageByArtId(Integer artId) {
			try {
				List<MessageVO> messageVO = messageRepository.findByMessageArtIdOrderByMessageTimestampAsc(artId);
				return messageVO;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		//（轉換為 DTO）
		public List<MessageDTO> getAllMessageByArtIdDTO (List<MessageVO> messageList) {
			return messageList.stream()
					.map(message -> new MessageDTO(message.getMessageContent(), message.getMessageTimestamp(),message.getUserVO().getUserNickname()))
					.collect(Collectors.toList());
		}
}
