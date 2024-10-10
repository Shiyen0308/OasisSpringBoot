package com.oasis.model;

import java.sql.Timestamp;

public class ArtListDTO extends BaseDTO {
		private Integer gameId;
		
		public ArtListDTO() {
			
		}
		public ArtListDTO(Integer artId,String artTitle, Timestamp  artTimestamp, Integer gameId) {
			super(artId,artTitle,artTimestamp);
			this.gameId = gameId;
		}
		public Integer getGameId() {
			return gameId;
		}
		public void setGameId(Integer gameId) {
			this.gameId = gameId;
		}
		
}
