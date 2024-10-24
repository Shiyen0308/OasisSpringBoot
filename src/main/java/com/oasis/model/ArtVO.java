package com.oasis.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
@Builder
@Table(name = "art")
public class ArtVO implements java.io.Serializable {
	@Id
	@Column(name = "art_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer artId;
	@Column(name = "art_title")
	@NotBlank
	private String artTitle;
	@Column(name = "art_content", columnDefinition = "mediumtext")
	@NotBlank
	private String artContent;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "art_timestamp")
	private Timestamp artTimestamp;
	@Column(name = "art_reply")
	private Integer artReply;
	@Column(name = "art_status")
	private Integer artStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "art_user_id", referencedColumnName = "user_id")
	private UserVO userVO;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "art_game_id", referencedColumnName = "game_id")
	private GameVO gameVO;

}
