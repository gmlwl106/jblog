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
	public List<BlogVo> blogSearch(String keyword, String kwdOpt) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		map.put("kwdOpt", kwdOpt);
		return blogDao.blogSelect(map);
	}

}
