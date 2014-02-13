package com.ww.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ww.exceptions.DAOException;
import com.ww.pojo.ResourceRole;
import com.ww.pojo.SResource;
import com.ww.pojo.SRole;
import com.ww.pojo.SUser;

@Repository
public class UserDAO {
	/**
	 * injected wed db template
	 * */
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@SuppressWarnings("unchecked")
	public List<SUser> getAllUsers() {
		List<SUser> userlist = (List<SUser>) sqlSessionTemplate.selectList("ww.sec.user.getAllUsers");
		return userlist;
	}

	public int saveUser(SUser user) {
		int updated = sqlSessionTemplate.insert("ww.sec.user.saveuser", user);
		return updated;
	}

	public int saveRole(SRole role) {  
		int updated = sqlSessionTemplate.insert("ww.sec.user.saveRole", role);
		return updated;
	}

	public int saveResource(SResource rs) {
		int updated = sqlSessionTemplate.insert("ww.sec.user.saveResource", rs);
		return updated;
	}

	public List<SRole> getAllRoles() {
		List<SRole> glist = (List<SRole>) sqlSessionTemplate.selectList("ww.sec.user.getAllRoles");
		return glist;
	}

	public void saveRelation(Map params) throws DAOException {
		try {
			sqlSessionTemplate.insert("ww.sec.user.saveRelation", params);
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}
	
	public void saveResRelation(List<ResourceRole>list) throws DAOException {
		try {
			sqlSessionTemplate.insert("ww.sec.user.saveResRelation", list);
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}
}
