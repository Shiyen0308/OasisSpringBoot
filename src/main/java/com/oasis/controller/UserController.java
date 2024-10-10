package com.oasis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.model.UserVO;
import com.oasis.service.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
    private HttpSession session ;
    
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
	 @GetMapping("/user")
	 public ResponseEntity<?> getUser(HttpSession session){
		 UserVO user = (UserVO) session.getAttribute("user");
		 if (user != null) {
	            return ResponseEntity.ok(Map.of("loggedIn", true, "username", user.getUserNickname()));
	        } else {
	            return ResponseEntity.ok(Map.of("loggedIn", false));
	        }
	 }
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
	 
}
