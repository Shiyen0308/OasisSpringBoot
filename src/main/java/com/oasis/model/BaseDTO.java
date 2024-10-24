package com.oasis.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseDTO {
	 private Integer artId;
	 private String artTitle;
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 private Timestamp artTimestamp;
	 
	 public BaseDTO(){
		 
	 }
	 
	 public BaseDTO(Integer artId, String artTitle,Timestamp artTimestamp ){
		 this.artId = artId;
		 this.artTitle = artTitle;
		 this.artTimestamp =artTimestamp;
	 }

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

	public Timestamp getArtTimestamp() {
		return artTimestamp;
	}

	public void setArtTimestamp(Timestamp artTimestamp) {
		this.artTimestamp = artTimestamp;
	}

	
	 
	 
}
