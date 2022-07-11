package com.javaex.dao;

import java.util.List;
import java.util.Map;

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
	
	//블로그 헤더(blogTitle, id) 가져오기
	public BlogVo getBlogHeader(String id) {
		System.out.println("BlogDao->getBlogHeader()");
		return sqlSession.selectOne("blog.getHeader", id);
	}
	
	//블로그 정보 가져오기
	public BlogVo getBlog(String id) {
		System.out.println("BlogDao->getBlog()");
		return sqlSession.selectOne("blog.getBlog", id);
	}
	
	//블로그 정보 수정
	public int blogUpdate(BlogVo blogVo) {
		System.out.println("BlogDao->blogUpdate()");
		return sqlSession.update("blog.update", blogVo);
	}

	//(메인) 블로그 검색
	public List<BlogVo> blogSelect(Map<String, Object> map) {
		return sqlSession.selectList("blog.select", map);
	}

	//(메인) 블로그 갯수
	public int getBlogCnt(Map<String, Object> map) {
		return sqlSession.selectOne("blog.getCnt", map);
	}

}
