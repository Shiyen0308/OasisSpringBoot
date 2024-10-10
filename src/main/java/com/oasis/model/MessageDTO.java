package com.oasis.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageDTO {
	private String messageContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp messageTimestamp;
	private String userNickname;
	
	public MessageDTO() {
		
	}
	
	public MessageDTO(String messageContent,Timestamp messageTimestamp,String userNickname) {
		this.messageContent = messageContent;
		this.messageTimestamp = messageTimestamp;
		this.userNickname = userNickname;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Timestamp getMessageTimestamp() {
		return messageTimestamp;
	}

	public void setMessageTimestamp(Timestamp messageTimestamp) {
		this.messageTimestamp = messageTimestamp;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
	
}
