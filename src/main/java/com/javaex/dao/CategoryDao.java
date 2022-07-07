package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	//카테고리 전체 정보 가져오기
	public List<CategoryVo> getCategorySet(String id) {
		System.out.println("CategoryDao->getCategorySet()");
		return sqlSession.selectList("category.getCateSet", id);
	}
	
	//카테고리 추가
	public int categoryInsert(CategoryVo cateVo) {
		System.out.println("CategoryDao->categoryInsert(cateVo)");
		return sqlSession.insert("category.cateInsert", cateVo);
	}

	//카테고리 1개 정보 가져오기
	public CategoryVo getCategoryOne(int cateNo) {
		System.out.println("CategoryDao->getCategoryOne()");
		return sqlSession.selectOne("category.getCate", cateNo);
	}
	
	//카테고리 삭제
	public int categoryDelete(CategoryVo cateVo) {
		System.out.println("CategoryDao->categoryDelete()");
		return sqlSession.delete("category.delete", cateVo);
	}
	
	
//================================================================================	
	//(회원가입) 카테고리 추가
	public int categoryInsert(String id) {
		System.out.println("CategoryDao->categoryInsert(id)");
		return sqlSession.insert("category.insert", id);
	}
	
	//(블로그메인) 카테고리 정보 가져오기
	public List<CategoryVo> getCategory(String id) {
		System.out.println("CategoryDao->getCategory()");
		return sqlSession.selectList("category.getCategory", id);
	}

	

	

}
