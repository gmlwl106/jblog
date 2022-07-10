package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {

	@Autowired
	SqlSession sqlSession;
	
	
	//코멘트 리스트 가져오기
	public List<CommentVo> getCmtList(int postNo) {
		System.out.println("CommentDao->getCmtList()");
		return sqlSession.selectList("comments.getCmtList", postNo);
	}
		
	//코멘트 추가
	public int cmtInsert(CommentVo cmtVo) {
		System.out.println("CommentDao->cmtInsert()");
		return sqlSession.insert("comments.insert", cmtVo);
	}

	//코멘트 가져오기
	public CommentVo getComment(int cmtNo) {
		System.out.println("CommentDao->getComment()");
		return sqlSession.selectOne("comments.getComment", cmtNo);
	}

	//코멘트 삭제
	public int cmtDelete(int cmtNo) {
		System.out.println("CommentDao->cmtDelete()");
		return sqlSession.delete("comments.delete", cmtNo);
	}


}
