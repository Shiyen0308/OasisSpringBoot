package com.oasis.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "message")
public class MessageVO implements java.io.Serializable {
	
	@Id
	@Column(name ="message_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer messageId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_user_id", referencedColumnName = "user_id")
	private UserVO userVO ;

	@Column(name ="message_art_id")
	private Integer messageArtId;
	
	@Column(name ="message_content",columnDefinition = "mediumtext")
	private String messageContent;
	
	@Column(name ="message_timestamp")
	private Timestamp messageTimestamp;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
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

	public Integer getMessageArtId() {
		return messageArtId;
	}

	public void setMessageArtId(Integer messageArtId) {
		this.messageArtId = messageArtId;
	}

	
	
}
	

