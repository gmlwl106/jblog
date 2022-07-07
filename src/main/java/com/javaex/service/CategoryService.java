package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	CategoryDao cateDao;

	//카테고리 가져오기
	public List<CategoryVo> getCategorySet(String id) {
		System.out.println("CategoryService->getCategory()");
		return cateDao.getCategorySet(id);
	}

	//카테고리 추가하기
	public CategoryVo categoryAdd(CategoryVo cateVo) {
		System.out.println("CategoryService->categoryAdd()");
		int count = cateDao.categoryInsert(cateVo);
		System.out.println(cateVo.getCateNo());
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

}
