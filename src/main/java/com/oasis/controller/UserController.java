package com.oasis.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oasis.model.UserVO;
import com.oasis.repository.UserRepository;
import com.oasis.service.EmailService;
import com.oasis.service.RedisService;
import com.oasis.service.UploadImgService;
import com.oasis.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	@Autowired
	private UploadImgService uploadImgService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private RedisService redisService;

	// 登入
	@PostMapping("/login")
	public ResponseEntity<String> check(@RequestBody Map<String, String> userData) {
		String email = userData.get("email");
		String password = userData.get("password");

		if (userService.check(email, password)) {
			UserVO user = userService.getUser(email);
			session.setAttribute("user", user);
			return ResponseEntity.ok("Login successful");
		} else {
			return ResponseEntity.status(401).body("Invalid email or password");
		}
	}
	// 確認是否登入
	@GetMapping("/user")
	public ResponseEntity<?> getUser(HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("user");
		if (user != null) {
			return ResponseEntity.ok(Map.of("loggedIn", true, "username", user.getUserNickname()));
		} else {
			return ResponseEntity.ok(Map.of("loggedIn", false));
		}
	}

	// 登出
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		try {
			session.invalidate();
			return ResponseEntity.ok("Logout successful");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
		}
	}

	// 發送驗證碼
	@PostMapping("/sendCheckPassword")
	public ResponseEntity<Map<String,Boolean>> sendCheckPassword(@RequestBody Map<String, String> email) {
		String userEmail = email.get("email");
		String checkPassword = String.format("%06d", new Random().nextInt(999999));
		emailService.SendCheckPassword(userEmail, checkPassword);
		redisService.saveCheckPassword(userEmail, checkPassword);
		 return ResponseEntity.status(HttpStatus.OK)
		            .body(Map.of("success", true));
	}

	// 註冊
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestParam Map<String, String> userdata,
			@RequestParam MultipartFile avatar) {
		String userNickname = userdata.get("nickName");
		String userEmail = userdata.get("email");
		String userPassword = userdata.get("password");
		String checkPassword = userdata.get("checkPassword");
		String userAvatar = null;
		// 驗證
		String redieCheckPassword = redisService.getCheckPassword(userEmail);
			if(!checkPassword.equals(redieCheckPassword)) {
				  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("驗證碼不正確");
			}
		// 圖片上傳
		try {
			if(avatar == null) {
				userAvatar = "/img/user/unknown.jpg";
			}else {
				userAvatar = uploadImgService.uploadImg(avatar);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("頭像上傳失敗");
		}
		// 驗證信箱是否已存在
		if (userRepository.existsByUserEmail(userEmail)) {
			System.out.println("電子郵件已存在");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("註冊失敗：電子郵件已存在");
		}
		UserVO user = UserVO.builder().userEmail(userEmail).userNickname(userNickname).userPassword(userPassword)
				.userAvatar(userAvatar).build();

		Boolean registrationSuccess = userService.registerUser(user);
		if (registrationSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body("註冊成功");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("註冊失敗");
		}
	}

}
