package com.oasis.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="game")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameVO implements java.io.Serializable{
	@Id
	@Column(name = "game_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gameId;
	@Column(name = "game_name")
	private String gameName;
	@Column(name = "game_img")
	private String gameImg;
	
	
	
}
