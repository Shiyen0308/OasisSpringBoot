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
@Table(name = "message")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageVO implements java.io.Serializable {
	
	@Id
	@Column(name ="message_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer messageId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "message_user_id", referencedColumnName = "user_id")
	private UserVO userVO ;

	@Column(name ="message_art_id")
	private Integer messageArtId;
	
	@Column(name ="message_content",columnDefinition = "mediumtext")
	@NotBlank
	private String messageContent;
	
	@Column(name ="message_timestamp")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp messageTimestamp;

	
	
	
}
	

