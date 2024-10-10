package com.oasis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis.model.ArtVO;

public interface ArtRepository extends JpaRepository<ArtVO, Integer> {
	List<ArtVO> findByGameVO_GameIdAndArtReplyIsNullOrderByArtTimestampDesc(Integer gameId);
	List<ArtVO> findByArtReplyOrderByArtTimestampAsc(Integer artId);
	List<ArtVO> findByUserVO_UserIdOrderByArtTimestampAsc(Integer userId);
}
