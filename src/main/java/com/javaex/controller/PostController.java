package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.PostService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
public class PostController {
	
	@Autowired
	PostService postService;

	//글쓰기 폼
	@RequestMapping(value="/{id}/admin/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminWriteForm(Model model, @PathVariable String id) {
		System.out.println("PostController->adminWriteForm()");
		List<CategoryVo> cateList = postService.getCategory(id);
		model.addAttribute("cateList", cateList);
		return "blog/admin/blog-admin-write";
	}
	
	//글쓰기
	@RequestMapping(value="/{id}/admin/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String postWrite(@ModelAttribute PostVo postVo, @PathVariable String id) {
		System.out.println("PostController->postWrite()");
		System.out.println(postVo);
		postService.postWrite(postVo);
		return "redirect:/"+id+"/admin/writeForm";
	}
}
