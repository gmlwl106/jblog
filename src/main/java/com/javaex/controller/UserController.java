package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	//로그인 폼
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		return "user/loginForm";
	}

	//회원가입 폼
	@RequestMapping(value="/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		return "user/joinForm";
	}
	
	//회원가입
	public String join() {
		return "";
	}
}
