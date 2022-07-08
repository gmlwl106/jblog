package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	SqlSession sqlSession;

	//글 추가하기
	public int postInsert(PostVo postVo) {
		System.out.println("PostDao->postInsert()");
		return sqlSession.insert("post.insert", postVo);
	}

	//(블로그메인) 글 제목 가져오기
	public List<PostVo> getPostList(int cateNo) {
		System.out.println("PostDao->getPostList()");
		return sqlSession.selectList("post.getList", cateNo);
	}
	
	//(블로그메인) 최신글 가져오기
	public PostVo getRecentPost(int cateNo) {
		System.out.println("PostDao->getRecentPost()");
		return sqlSession.selectOne("post.getRecentPost", cateNo);
	}

	//(글 클릭했을때) 글 가져오기
	public PostVo getPost(int postNo) {
		System.out.println("PostDao->getPost()");
		return sqlSession.selectOne("post.getPost", postNo);
	}

}
