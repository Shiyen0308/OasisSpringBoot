package com.oasis.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	@Autowired
    private StringRedisTemplate redisTemplate;
	
	public void saveCheckPassword(String userEmail, String checkPassword) {
		  redisTemplate.opsForValue().set(userEmail, checkPassword, 5, TimeUnit.MINUTES);
	}
	 public String getCheckPassword(String userEmail) {
	        return redisTemplate.opsForValue().get(userEmail);
	    }
}
