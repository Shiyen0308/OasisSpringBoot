package com.oasis.model;

import java.sql.Timestamp;
import java.util.List;

public class ArtDTO extends ForumArtDTO {
	private String artContent;
	private String userAvatar;
	private List<MessageDTO> messageDTO;

	public ArtDTO() {
		
	}

	public ArtDTO(Integer artId, String artTitle, Timestamp artTimestamp, String userNickname,String artContent,String userAvatar,List<MessageDTO> messageDTO) {
		super(artId,artTitle,artTimestamp,userNickname);
		this.artContent = artContent;
		this.userAvatar = userAvatar;
		this.messageDTO=messageDTO;
	}

	public String getArtContent() {
		return artContent;
	}

	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public List<MessageDTO> getMessageDTO() {
		return messageDTO;
	}

	public void setMessageDTO(List<MessageDTO> messageDTO) {
		this.messageDTO = messageDTO;
	}
	
	
}
