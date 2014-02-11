package com.ww.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ww.controllers.workflow.PersonalController;
import com.ww.dao.UserDAO;
import com.ww.exceptions.BizException;
import com.ww.exceptions.DAOException;
import com.ww.pojo.SRole;
import com.ww.pojo.SUser;

@Service
public class UserServices {
	private static final Logger LOG = Logger.getLogger(UserServices.class);
	@Autowired
	private UserDAO userdao;

	public List<SUser> getAllUsers() {
		return userdao.getAllUsers();
	}

	public List<SRole> getAllRoles() {
		return userdao.getAllRoles();
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveUser(SUser user) throws BizException {
		try {
			int userid = userdao.save(user);
		
			int groupid = user.getRole();
			LOG.info("userid:"+userid+"--Roleid:"+groupid);
			
			Map map = new HashMap();
			map.put("user_id", userid);
			map.put("role_id", groupid);
			map.put("create_by", user.getCreate_by());
			map.put("updated_by", user.getUpdated_by());
			userdao.saveRelation(map);
		} catch (DAOException e) {
			throw new BizException(e);
		}
	}

	public int saveRole(SRole role) {
		return userdao.saveRole(role);

	}

}
