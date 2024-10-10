package com.oasis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis.model.MessageVO;

public interface MessageRepository extends JpaRepository<MessageVO, Integer> {
	 List<MessageVO> findByMessageArtIdOrderByMessageTimestampAsc(Integer artId);
}
