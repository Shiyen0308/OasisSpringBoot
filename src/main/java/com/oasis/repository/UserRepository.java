package com.oasis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis.model.UserVO;

public interface UserRepository extends JpaRepository<UserVO, Integer> {
	public UserVO findByUserEmail(String useremail);
}
