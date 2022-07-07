package com.javaex.dao;

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

}
