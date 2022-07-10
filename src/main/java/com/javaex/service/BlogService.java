package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao cateDao;
	@Autowired
	private PostDao postDao;
	
	//블로그 정보 가져오기 (메인)
	public Map<String, Object> getBlog(String id, int cateNo, int postNo, int crtPage) {
		System.out.println("BlogController->blogMain()");

		
		//페이징============================================================================
		//페이지당 글 갯수
		int listCnt = 5;
		//현재페이지
		crtPage = (crtPage>0)? crtPage : (crtPage=1);
		//시작번호
		int startRnum = (crtPage-1)*listCnt+1;
		//끝번호
		int endRnum = (startRnum+listCnt)-1;
		
		//글 갯수
		int totalCnt = postDao.selectCnt(id, cateNo);
		//페이지당 버튼 갯수
		int pageBtnCnt = 5;
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage / (double)pageBtnCnt)*pageBtnCnt;
		//시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo-pageBtnCnt) + 1;
		//다음 화살표 유무
		boolean next = false;
		if((listCnt*endPageBtnNo) < totalCnt) {
			next = true;
		} else {
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		//페이징============================================================================
		
		
		
		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap.put("headerVo", blogDao.getBlogHeader(id)); //헤더 (id, blogTitle)
		blogMap.put("blogVo", blogDao.getBlog(id)); //블로그 (userName, logoFile)
		blogMap.put("cateList", cateDao.getCategory(id)); //카테고리 (cateNo, cateName)
		blogMap.put("postList", postDao.getPostList(startRnum, endRnum, id, cateNo)); //글제목 (postNo, cateNo, postTitle, regDate)
		if(postNo == 0) {
			blogMap.put("postVo", postDao.getRecentPost(id, cateNo)); //최신글 (postNo, postTitle, postContent, regDate)
		} else {
			blogMap.put("postVo", postDao.getPost(postNo)); //본문 (postNo, postTitle, postContent, regDate)
		}
		blogMap.put("prev", prev); //이전 화살표
		blogMap.put("next", next); //다음 화살표
		blogMap.put("startPageBtnNo", startPageBtnNo); //시작 번호
		blogMap.put("endPageBtnNo", endPageBtnNo); //마지막 번호
		return blogMap;
	}

	//블로그 정보 가져오기 (내블로그 관리)
	public Map<String, Object> getBlogSet(String id) {
		System.out.println("BlogController->getBlogSet()");
		Map<String, Object> blogMap = new HashMap<String, Object>();
		BlogVo headerVo = blogDao.getBlogHeader(id);
		BlogVo blogVo = blogDao.getBlog(id);
		blogMap.put("headerVo", headerVo);
		blogMap.put("blogVo", blogVo);
		return blogMap;
	}

	//블로그 기본설정 수정
	public int blogModify(String id, String blogTitle, MultipartFile file) {
		System.out.println("BlogController->blogModify()");
		
		String saveDir = "C:\\javaStudy\\upload";
		
		//오리지날 파일명
		String orgName = file.getOriginalFilename();
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		//현재시간+랜덤UUID+확장자
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		//파일경로(디렉토리+저장파일명)
		String filePath = saveDir + "\\" + saveName;
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(id);
		blogVo.setBlogTitle(blogTitle);
		blogVo.setLogoFile("upload/" + saveName);
		
		//DB 저장
		int count = blogDao.blogUpdate(blogVo);
		
		//파일 저장
		try {
			
			byte[] fileData = file.getBytes();
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
}
