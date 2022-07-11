package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.MainService;
import com.javaex.vo.BlogVo;

@Controller
public class MainController {
	
	@Autowired
	MainService mainService;
	
	@RequestMapping(value="/")
	public String main() {
		return "main/index";
	}
	
	//블로그 검색
	@RequestMapping(value="/search", method = {RequestMethod.GET, RequestMethod.POST})
	public String blogSearch(Model model,
							@RequestParam("keyword") String keyword,
							@RequestParam(value="kwdOpt", required = false, defaultValue = "optTitle") String kwdOpt,
							@RequestParam(value="crtPage", required = false, defaultValue = "1") int crtPage) {
		List<BlogVo> bList = mainService.blogSearch(keyword, kwdOpt, crtPage);
		model.addAttribute("bList", bList);
		return "main/index";
	}
}
