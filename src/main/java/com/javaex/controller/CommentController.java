package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentService;
import com.javaex.vo.CommentVo;

@Controller
public class CommentController {

	@Autowired
	CommentService cmtService;
	
	
	//코멘트 리스트 가져오기
	@ResponseBody
	@RequestMapping(value="/{id}/comments", method = {RequestMethod.GET, RequestMethod.POST})
	public List<CommentVo> comments(@RequestParam(value="postNo", required=false, defaultValue="0") int postNo) {
		System.out.println("CommentConroller->comments");
		return cmtService.comments(postNo);
	}
	
	//코멘트 저장
	@ResponseBody
	@RequestMapping(value="/{id}/cmtUpload", method = {RequestMethod.GET, RequestMethod.POST})
	public CommentVo cmtUpload(@ModelAttribute CommentVo cmtVo) {
		System.out.println("CommentConroller->cmtUpload");
		return cmtService.cmtUpload(cmtVo);
	}
	
	//코멘트 삭제
	@ResponseBody
	@RequestMapping(value="/{id}/cmtDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public String cmtDelete(@RequestParam("cmtNo") int cmtNo) {
		System.out.println("CommentConroller->cmtDelete");
		return cmtService.cmtDelete(cmtNo);
	}
}
