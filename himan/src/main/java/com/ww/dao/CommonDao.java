package com.ww.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	public List<Map<String, Object>> getAllResource() {
		return null;
	}

	public List<String> getRoleByResourceId(Integer resourceid) {
		return null;
	}
}
