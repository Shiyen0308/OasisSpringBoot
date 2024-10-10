package com.oasis.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "art")
public class ArtVO implements java.io.Serializable {
	@Id
	@Column(name = "art_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer artId;
	@Column(name = "art_title")
	private String artTitle;
	@Column(name = "art_content", columnDefinition = "mediumtext")
	private String artContent;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "art_timestamp")
	private Timestamp artTimestamp;
	@Column(name = "art_reply")
	private Integer artReply;
	@Column(name = "art_status")
	private Integer artStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "art_user_id", referencedColumnName = "user_id")
	private UserVO userVO;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "art_game_id", referencedColumnName = "game_id")
	private GameVO gameVO;

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer artId) {
		this.artId = artId;
	}

	public String getArtTitle() {
		return artTitle;
	}

	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}

	public String getArtContent() {
		return artContent;
	}

	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}

	public Timestamp getArtTimestamp() {
		return artTimestamp;
	}

	public void setArtTimestamp(Timestamp artTimestamp) {
		this.artTimestamp = artTimestamp;
	}

	public Integer getArtReply() {
		return artReply;
	}

	public void setArtReply(Integer artReply) {
		this.artReply = artReply;
	}

	public Integer getArtStatus() {
		return artStatus;
	}

	public void setArtStatus(Integer artStatus) {
		this.artStatus = artStatus;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public GameVO getGameVO() {
		return gameVO;
	}

	public void setGameVO(GameVO gameVO) {
		this.gameVO = gameVO;
	}

}
