package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao cateDao;
	
	//블로그 정보 가져오기 (메인)
	public Map<String, Object> getBlog(String id) {
		System.out.println("BlogController->blogMain()");
		BlogVo blogVo = blogDao.getBlog(id);
		List<CategoryVo> cateList = cateDao.getCategory(id);
		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap.put("blogVo", blogVo);
		blogMap.put("cateList", cateList);
		
		return blogMap;
	}

	//블로그 정보 가져오기 (내블로그 관리)
	public Map<String, Object> getBlogSet(String id) {
		System.out.println("BlogController->getBlogSet()");
		Map<String, Object> blogMap = new HashMap<String, Object>();
		BlogVo blogVo = blogDao.getBlog(id);
		blogMap.put("blogVo", blogVo);
		return blogMap;
	}

	//블로그 기본설정 수정
	public int blogModify(String id, String blogTitle, MultipartFile file) {
		System.out.println("BlogController->blogModify()");
		
		//String saveDir = "D:\\javaStudy\\upload"; //집
		String saveDir = "C:\\javaStudy\\upload"; //학원
		
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
		blogVo.setLogoFile(saveName);
		
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
