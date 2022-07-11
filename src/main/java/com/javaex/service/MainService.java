package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class MainService {
	
	@Autowired
	BlogDao blogDao;


	//블로그 검색
	public Map<String, Object> blogSearch(String keyword, String kwdOpt, int crtPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("kwdOpt", kwdOpt);
		
		
		
		//페이지당 글 갯수
		int listCnt = 5;
		//현재페이지
		crtPage = (crtPage>0)? crtPage : (crtPage=1);
		//시작번호
		int startRnum = (crtPage-1)*listCnt+1;
		//끝번호
		int endRnum = (startRnum+listCnt)-1;
		
		//글 갯수
		int totalCnt = blogDao.getBlogCnt(map);
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
		
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		List<BlogVo> bList = blogDao.blogSelect(map);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("bList", bList);
		bMap.put("startPageBtnNo", startPageBtnNo);
		bMap.put("endPageBtnNo", endPageBtnNo);
		bMap.put("prev", prev);
		bMap.put("next", next);
		return bMap;
	}
}
