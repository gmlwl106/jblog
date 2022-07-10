package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	CategoryDao cateDao;
	@Autowired
	BlogDao blogDao;
	

	//카테고리 가져오기
	public List<CategoryVo> getCategorySet(String id) {
		System.out.println("CategoryService->getCategory()");
		return cateDao.getCategorySet(id);
	}

	//카테고리 추가하기
	public CategoryVo categoryAdd(CategoryVo cateVo) {
		System.out.println("CategoryService->categoryAdd()");
		int count = cateDao.categoryInsert(cateVo);
		return cateDao.getCategoryOne(cateVo.getCateNo());
	}

	//카테고리 삭제하기
	public String categoryDelete(CategoryVo cateVo) {
		System.out.println("CategoryService->categoryDelete()");
		if(cateVo.getPostCnt() != 0) {
			return "fail";
		} else {
			int count = cateDao.categoryDelete(cateVo);
			
			if(count < 0) {
				return "fail";
			} else {
				return "success";
			}
		}
	}
	
	//헤더 가져오기 (blogDao)
	public Map<String, Object> getHeader(String id) {
		BlogVo headerVo = blogDao.getBlogHeader(id);
		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap.put("headerVo", headerVo);
		return blogMap;
	}

}
