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
	
	//블로그 카테고리 추가
	public int categoryInsert(String id) {
		System.out.println("CategoryDao->categoryInsert()");
		return sqlSession.insert("category.insert", id);
	}
	
	//블로그 카테고리 정보 가져오기
	public List<CategoryVo> getCategory(String id) {
		System.out.println("CategoryDao->getCategory()");
		return sqlSession.selectList("category.getCategory", id);
	}
}
