package com.oasis.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oasis.model.ArtDTO;
import com.oasis.model.ArtListDTO;
import com.oasis.model.ArtVO;
import com.oasis.model.ForumArtDTO;
import com.oasis.model.MessageDTO;
import com.oasis.model.MessageVO;
import com.oasis.repository.ArtRepository;

@Service
public class ArtService {
	@Autowired
	private ArtRepository artRepository;
	@Autowired
	private MessageService messageService;

	// 新增文章
	@Transactional
	public Integer CreateArt(ArtVO artVO) {
		try {
			ArtVO savedArt = artRepository.save(artVO);
			return savedArt.getArtId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 更新文章
	@Transactional
	public ArtVO updateArt(Integer artId, String artCotent,Timestamp artTimestamp) {
		try {
			 ArtVO art = artRepository.findById(artId)
			            .orElseThrow(() -> new RuntimeException("文章不存在"));
			 art.setArtContent(artCotent);
			 art.setArtTimestamp(artTimestamp);
			return artRepository.save(art);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 刪除文章
	@Transactional
	public void deleteArt(Integer artId) {
		try {
			Optional<ArtVO> optionalArt = artRepository.findById(artId);
			if (optionalArt.isPresent()) {
				ArtVO artVO = optionalArt.get();
				artVO.setArtStatus(1);
			} else {
				System.out.println("Article not found for ID: " + artId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 依遊戲 ID 查詢文章
	public List<ArtVO> getAllArtByGameId(Integer gameId) {
		try {
			List<ArtVO> artVO = artRepository.findByGameVO_GameIdAndArtReplyIsNullOrderByArtTimestampDesc(gameId);
			return artVO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//（轉換為 DTO）
	public List<ForumArtDTO> getAllArtsByGameId(List<ArtVO> artList) {
		return artList.stream()
				.map(art -> new ForumArtDTO(art.getArtId(), art.getArtTitle(), art.getArtTimestamp(),art.getUserVO().getUserNickname()))
				.collect(Collectors.toList());
	}

	// 依照使用者 ID 查詢文章
	public List<ArtListDTO> getAllArtByUserId(Integer userId) {
		try {
			 List<ArtVO> artVO   = artRepository.findByUserVO_UserIdOrderByArtTimestampAsc(userId);
			return artVO.stream()
				.map(art -> new ArtListDTO(art.getArtId(), art.getArtTitle(), art.getArtTimestamp(),art.getGameVO().getGameId()))
				.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 查詢單篇文章&留言
	public ArtDTO getFirstArtWithMessage(Integer artId) {
		try {
			Optional<ArtVO> optionalArt = artRepository.findById(artId);
		    ArtVO art =  optionalArt.orElse(null);
		    List<MessageVO> message = messageService.getAllMessageByArtId(artId);
		    List<MessageDTO> messageDTO = messageService.getAllMessageByArtIdDTO(message);
		    ArtDTO artDTO = new ArtDTO();
		    artDTO.setArtId(art.getArtId());
		    artDTO.setArtTitle(art.getArtTitle());
		    if(art.getArtStatus() == 0) {
		    	artDTO.setArtContent(art.getArtContent());
		    }else {
		    	artDTO.setArtContent("此文章已被刪除");
		    }
		    artDTO.setArtContent(art.getArtContent());
		    artDTO.setArtTimestamp(art.getArtTimestamp());
		    artDTO.setUserNickname(art.getUserVO().getUserNickname());
		    artDTO.setUserAvatar(art.getUserVO().getUserAvatar());
		    artDTO.setMessageDTO(messageDTO);
		    return artDTO;
		    
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 查詢回覆文章&留言
	public List<ArtDTO> getReplyArtWithMessage(Integer artId) {
	    try {
	        
	        List<ArtVO> artList = artRepository.findByArtReplyOrderByArtTimestampAsc(artId);
	        
	        
	        List<ArtDTO> artDTOs = artList.stream().map(art -> { 
	            ArtDTO artDTO = new ArtDTO();
	            artDTO.setArtId(art.getArtId());
	            artDTO.setArtTitle(art.getArtTitle());
	            
	            
	            if (art.getArtStatus() == 0) {
	                artDTO.setArtContent(art.getArtContent());
	            } else {
	                artDTO.setArtContent("此文章已被刪除");
	            }
	            
	            artDTO.setArtTimestamp(art.getArtTimestamp());
	            artDTO.setUserNickname(art.getUserVO().getUserNickname());
	            artDTO.setUserAvatar(art.getUserVO().getUserAvatar());
	            
	            
	            List<MessageVO> messages = messageService.getAllMessageByArtId(art.getArtId());
	            List<MessageDTO> messageDTOs = messageService.getAllMessageByArtIdDTO(messages);
	            
	            artDTO.setMessageDTO(messageDTOs); 
	            
	            return artDTO;  
	        }).collect(Collectors.toList());

	        return artDTOs;  

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;  
	    }
	}

	
		
		
		
	}


