package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("UserService->join()");
		return userDao.userInsert(userVo);
	}
	
	//아이디 중복체크
	public String idCk(String id) {
		System.out.println("UserService->idCk()");
		UserVo userVo = userDao.idSelect(id);

		if(userVo == null) {
			return "success";
		} else {
			return "fail";
		}
	}
}
