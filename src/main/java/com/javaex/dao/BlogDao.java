package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	//블로그 추가
	public int blogInsert(String id) {
		System.out.println("BlogDao->blogInsert()");
		return sqlSession.insert("blog.insert", id);
	}
	
	//블로그 정보 가져오기
	public BlogVo getBlog(String id) {
		System.out.println("BlogDao->getBlog()");
		return sqlSession.selectOne("blog.getBlog", id);
	}
}
