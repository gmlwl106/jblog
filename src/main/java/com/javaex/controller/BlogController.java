package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	//블로그 메인화면
	@RequestMapping(value="/{id}", method= {RequestMethod.POST, RequestMethod.GET})
	public String blogMain(Model model, @PathVariable String id) {
		System.out.println("BlogController->blogMain()");
		Map<String, Object> blogMap = blogService.getBlog(id);
		System.out.println(blogMap);
		model.addAttribute("blogMap", blogMap);
		return "blog/blog-main";
	}
}
