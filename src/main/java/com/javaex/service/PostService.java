package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	PostDao postDao;
	@Autowired
	CategoryDao cateDao;
	
	//카테고리 이름 가져오기
	public List<CategoryVo> getCategory(String id) {
		System.out.println("PostService->getCategory()");
		return cateDao.getCategory(id);
	}

	//글쓰기
	public int postWrite(PostVo postVo) {
		System.out.println("PostService->postWrite()");
		return postDao.postInsert(postVo);
	}

}
