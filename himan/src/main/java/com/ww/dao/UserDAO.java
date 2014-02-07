package com.ww.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ww.pojo.Group;
import com.ww.pojo.User;

@Repository
public class UserDAO {
	/**
	 * injected wed db template
	 * */
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		List<User> userlist = (List<User>) sqlSessionTemplate.selectList("ww.user.getAllUser");
		return userlist;
	}

	public int save(User user) {
		int updated = sqlSessionTemplate.insert("ww.user.saveuser", user);
		return updated;
	}

	public int saveGroup(Group group) {
		int updated = sqlSessionTemplate.insert("ww.user.savegroup", group);
		return updated;
	}

	public List<Group> getAllGroups() {
		List<Group> glist = (List<Group>) sqlSessionTemplate.selectList("ww.user.getAllGroup");
		return glist ;
	}

	public void saveRelation(Map params) {
		
		sqlSessionTemplate.insert("ww.user.saveRelation", params);
		
	}
}
