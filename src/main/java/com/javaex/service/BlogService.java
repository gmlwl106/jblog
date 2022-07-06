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
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao cateDao;
	
	//블로그 정보 가져오기
	public Map<String, Object> getBlog(String id) {
		System.out.println("BlogController->blogMain()");
		BlogVo blogVo = blogDao.getBlog(id);
		List<CategoryVo> cateList = cateDao.getCategory(id);
		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap.put("blogVo", blogVo);
		blogMap.put("cateList", cateList);
		
		return blogMap;
	}
}
