package com.oasis.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO implements java.io.Serializable {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Email
	@Column(name = "user_email",unique = true)
	private String userEmail;
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "只能包含字母和數字")
	@Size(min= 8, max =12,  message = "密碼長度必須在 8 到 12 個字符之間")
	@Column(name = "user_password")
	private String userPassword;
	@NotBlank
	@Column(name = "user_nickname")
	private String userNickname;
	@NotBlank
	@Column(name = "user_avatar")
	private String userAvatar;

	@OneToMany(mappedBy = "userVO",fetch = FetchType.EAGER)
	private List<ArtVO> artList;
	
	

}
