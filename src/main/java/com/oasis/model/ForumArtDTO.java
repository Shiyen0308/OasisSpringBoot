package com.oasis.model;

import java.sql.Timestamp;

public class ForumArtDTO extends BaseDTO {
	private String userNickname;

	public ForumArtDTO() {

	}

	public ForumArtDTO(Integer artId, String artTitle, Timestamp artTimestamp, String userNickname) {
			super(artId,artTitle,artTimestamp);
			this.userNickname = userNickname;
			
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
}
