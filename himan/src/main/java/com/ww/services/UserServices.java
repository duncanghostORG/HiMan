package com.ww.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ww.dao.UserDAO;
import com.ww.exceptions.BizException;
import com.ww.exceptions.DAOException;
import com.ww.pojo.ResourceRole;
import com.ww.pojo.SResource;
import com.ww.pojo.SRole;
import com.ww.pojo.SUser;

@Service
public class UserServices {
	private static final Logger LOG = Logger.getLogger(UserServices.class);
	@Autowired
	private UserDAO userdao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private SaltSource saltSource; 
	

	public List<SUser> getAllUsers() {
		return userdao.getAllUsers();
	}

	public List<SRole> getAllRoles() {
		return userdao.getAllRoles();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUser(SUser user) throws BizException {
		try {
			
			String encodedPwd = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPwd);
			userdao.saveUser(user);
			int userid = user.getId();
			int groupid = user.getRole();
			LOG.info("userid:" + userid + "--Roleid:" + groupid);

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
		int roleid = userdao.saveRole(role);
		LOG.info("ROLEID:" + roleid);
		return roleid;

	}

	public void saveResource(SResource rs) throws BizException {
		try {
		userdao.saveResource(rs);
		int rsid = rs.getId();
		List<Integer> rolelist = rs.getRolelist();
		
		List<ResourceRole>list =new ArrayList<ResourceRole>();
		for(Integer role:rolelist){
			ResourceRole rr = new ResourceRole();
			rr.setResource_id(rsid);
			rr.setRole_id(role);
			rr.setUpdated_by(rs.getUpdated_by());
			rr.setCreate_by(rs.getCreate_by());
		}
		
			userdao.saveResRelation(list);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BizException(e);
		}
	}

}
