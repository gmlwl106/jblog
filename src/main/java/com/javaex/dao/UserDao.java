package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원 가져오기
	public UserVo getUser(UserVo userVo) {
		System.out.println("UserDao->getUser()");
		return sqlSession.selectOne("users.getUser", userVo);
	}
	
	//회원 추가
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao->UserInsert()");
		return sqlSession.insert("users.insert", userVo);
	}
	
	//중복 아이디 찾기
	public UserVo idSelect(String id) {
		System.out.println("UserDao->idSelect()");
		return sqlSession.selectOne("users.idSelect", id);
	}
}
