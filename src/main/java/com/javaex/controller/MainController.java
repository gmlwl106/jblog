package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	@RequestMapping(value="/blogSearch", method = {RequestMethod.GET, RequestMethod.POST})
	public List<BlogVo> blogSearch(@RequestParam(value="keyword", required = false, defaultValue = "") String keyword,
							@RequestParam(value="kwdOpt", required = false, defaultValue = "optTitle") String kwdOpt) {
		return mainService.blogSearch(keyword, kwdOpt);
	}
}
