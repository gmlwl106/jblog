package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao cateDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("UserService->join()");
		int count = userDao.userInsert(userVo);
		System.out.println(count);
		if(count > 0) {
			//user 생성에 성공했을 때 블로그 생성
			System.out.println(userVo.getId());
			blogDao.blogInsert(userVo.getId());
			cateDao.categoryInsert(userVo.getId());
		}
		return count;
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
