package com.oasis.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import com.oasis.model.UserVO;
import com.oasis.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Validated
public class UserService {
	 @Autowired
     private UserRepository userRepository;
	
	 // 登入確認
	 public boolean check(String email,String password) {
		 UserVO user = userRepository.findByUserEmail(email);
		 return user != null && user.getUserPassword().equals(password);
		 }
	 // 會員登入
	 public UserVO getUser(String email) {
		 UserVO user = userRepository.findByUserEmail(email);
		 return user != null ? user : null;
	 }
	 // 註冊
	 @Transactional
	 public boolean registerUser(@Valid  UserVO userVO) {
		 
		 try {
	            userRepository.save(userVO);
	            return true; 
	        } catch (Exception e) {
	            System.out.println("用戶保存失敗: " + e.getMessage());
	            return false; 
	        }
		 
	 }
	 
}
