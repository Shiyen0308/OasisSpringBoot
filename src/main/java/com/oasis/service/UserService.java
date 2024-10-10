package com.oasis.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasis.model.UserVO;
import com.oasis.repository.UserRepository;

@Service
public class UserService {
	 @Autowired
     private UserRepository userRepository;
	 
	 public boolean check(String email,String password) {
		 UserVO user = userRepository.findByUserEmail(email);
		 return user != null && user.getUserPassword().equals(password);
		 }
	 public UserVO getUser(String email) {
		 UserVO user = userRepository.findByUserEmail(email);
		 return user != null ? user : null;
	 }
	 
}
