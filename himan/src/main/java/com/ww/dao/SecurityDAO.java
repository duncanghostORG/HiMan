package com.ww.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.ww.controllers.workflow.PersonalController;
import com.ww.pojo.SRole;
import com.ww.pojo.SUser;

@Repository
public class SecurityDAO implements UserDetailsService {
	private static final Logger LOG = Logger.getLogger(SecurityDAO.class);
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userdetail = null;
		try {
			LOG.info("******************************loadUserByUsername******************");
			Map<String, String> map = new HashMap<String, String>();
			map.put("account", username);

			SUser user = (SUser) sqlSessionTemplate.selectOne("ww.sec.user.getUserByAccount", map);
			LOG.info("******************************Find user" + user.toString() + "******************");
			List<SRole> roleList = (List<SRole>) sqlSessionTemplate.selectList("ww.sec.user.getRolesByUser", map);
			LOG.info("******************************Find Roles" + roleList.size() + "******************");

			if (user != null) {
				String account = user.getAccount();
				String name = user.getName();
				String password = user.getPassword();
				String enable = user.getEnable();
				List<GrantedAuthority> authorityList = null;
				if (roleList != null && roleList.size() > 0) {
					authorityList = new ArrayList<GrantedAuthority>();
					for (SRole role : roleList) {
						String rolename = role.getName();
						GrantedAuthority authority = new SimpleGrantedAuthority(rolename);
						authorityList.add(authority);
					}

				}

				userdetail = new User(account, password, authorityList);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("", e);
		}
		return userdetail;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	

}
