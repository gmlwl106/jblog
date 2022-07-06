package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	SqlSession sqlSession;
	
	//회원 추가
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao->UserInsert()");
		return sqlSession.insert("users.insert", userVo);
	}
}
