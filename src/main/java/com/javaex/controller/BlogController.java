package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//블로그 메인화면
	@RequestMapping(value="/{id}", method= {RequestMethod.POST, RequestMethod.GET})
	public String blogMain(Model model, @PathVariable String id,
							@RequestParam(value="cateNo", required=false, defaultValue="0") int cateNo,
							@RequestParam(value="postNo", required=false, defaultValue="0") int postNo,
							@RequestParam(value="crtPage", required = false, defaultValue = "1") int crtPage) {
		System.out.println("BlogController->blogMain()");
		Map<String, Object> blogMap = blogService.getBlog(id, cateNo, postNo, crtPage);
		model.addAttribute("blogMap", blogMap);
		return "blog/blog-main";
	}
	
	//내블로그 관리
	@RequestMapping(value="/{id}/admin/basic", method= {RequestMethod.POST, RequestMethod.GET})
	public String adminBasic(Model model, @PathVariable String id) {
		System.out.println("BlogController->adminBasic()");
		Map<String, Object> blogMap = blogService.getBlogSet(id);
		model.addAttribute("blogMap", blogMap);
		return "blog/admin/blog-admin-basic";
	}
	
	//블로그 기본설정 수정
	@RequestMapping(value="/{id}/admin/blogModify", method= {RequestMethod.POST, RequestMethod.GET})
	public String blogModify(@PathVariable String id,
							@RequestParam(value="file") MultipartFile file,
							@RequestParam("blogTitle") String blogTitle) {
		System.out.println("BlogController->blogModify()");
		blogService.blogModify(id, blogTitle, file);
		return "redirect:/"+id+"/admin/basic";
	}
	
}
