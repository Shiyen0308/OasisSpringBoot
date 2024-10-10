package com.oasis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OasisController {
	// 首頁
	@GetMapping({"/","/home"})
	public String home() {
		return "redirect:/home/home.html";
	}
	// 登入
	@GetMapping("/login")
	public String login() {
		return "redirect:/login/login.html";
	}
	// 討論區
	@GetMapping("/forum/{gameId}")
	public String fowardToForumArtPage() {
		return "forward:/art/forumArt.html";
	}
	// 文章
	@GetMapping("/forum/{gameId}/art/{artId}")
	public String fowardToArtView() {
		return "forward:/art/artView.html";
	}
	// 發文
	@GetMapping("/post/{gameId}")
	public String fowardToPostArt() {
		return "forward:/art/postArt.html";
	}
	// 回覆
		@GetMapping("/reply/{gameId}/{artId}/{encodeTitle}")
		public String fowardToReplyArt() {
			return "forward:/art/replyArt.html";
		}
	//	修改文章
		@GetMapping("/update")
		public String fowardToUpdateArt() {
			return "forward:/art/updateArt.html";
		}
}
