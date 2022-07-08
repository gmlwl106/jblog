package com.javaex.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	PostDao postDao;
	@Autowired
	BlogDao blogDao;
	@Autowired
	CategoryDao cateDao;
	
	//글쓰기 폼 정보 가져오기 (헤더, 카테고리)
	public Map<String, Object> getCategory(String id) {
		System.out.println("PostService->getCategory()");
		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap.put("headerVo", blogDao.getBlogHeader(id));
		blogMap.put("cateList", cateDao.getCategory(id));
		return blogMap;
	}

	//글쓰기
	public int postWrite(PostVo postVo) {
		System.out.println("PostService->postWrite()");
		return postDao.postInsert(postVo);
	}

}
