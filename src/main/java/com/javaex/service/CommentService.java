package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.CommentVo;

@Service
public class CommentService {

	@Autowired
	CommentDao cmtDao;
	
	//코멘트 리스트 가져오기
	public List<CommentVo> comments(int postNo) {
		System.out.println("CommentService->comments");
		return cmtDao.getCmtList(postNo);
	}
	
	//코멘트 저장
	public CommentVo cmtUpload(CommentVo cmtVo) {
		System.out.println("CommentService->cmtUpload");
		cmtDao.cmtInsert(cmtVo);
		return cmtDao.getComment(cmtVo.getCmtNo());
	}

	//코멘트 삭제
	public String cmtDelete(int cmtNo) {
		System.out.println("CommentService->cmtDelete");
		int count = cmtDao.cmtDelete(cmtNo);
		
		if(count > 0) {
			return "success";
		} else {
			return "fail";
		}
	}


}
