package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService cateService;

	//내블로그 카테고리 관리 폼
	@RequestMapping(value="/{id}/admin/category", method= {RequestMethod.POST, RequestMethod.GET})
	public String adminCategoryForm(Model model, @PathVariable String id) {
		System.out.println("CategoryController->adminCategoryForm()");
		Map<String, Object> blogMap = cateService.getHeader(id);
		model.addAttribute("blogMap", blogMap);
		return "blog/admin/blog-admin-cate";
	}
	
	//카테고리 리스트 가져오기
	@ResponseBody
	@RequestMapping(value="/{id}/admin/getcategory", method= {RequestMethod.POST, RequestMethod.GET})
	public List<CategoryVo> adminCategory(Model model, @PathVariable String id) {
		System.out.println("CategoryController->adminCategory()");
		return cateService.getCategorySet(id);
	}
	
	//카테고리 추가하기
	@ResponseBody
	@RequestMapping(value="/{id}/admin/cateAdd", method= {RequestMethod.POST, RequestMethod.GET})
	public CategoryVo categoryAdd(@RequestBody CategoryVo cateVo) {
		System.out.println("CategoryController->categoryAdd()");
		return cateService.categoryAdd(cateVo);
	}
	
	//카테고리 삭제하기
	@ResponseBody
	@RequestMapping(value="/{id}/admin/cateDelete", method= {RequestMethod.POST, RequestMethod.GET})
	public String categoryDelete(@ModelAttribute CategoryVo cateVo) {
		System.out.println("CategoryController->categoryDelete()");
		return cateService.categoryDelete(cateVo);
	}
}
