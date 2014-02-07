package com.ww.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ww.dao.UserDAO;
import com.ww.pojo.Group;
import com.ww.pojo.User;

@Service
public class UserServices {
	@Autowired
	private UserDAO userdao;

	public List<User> getAllUsers() {
		return userdao.getAllUsers();
	}

	public List<Group> getAllGroups() {
		return userdao.getAllGroups();
	}

	public void save(User user) {
		userdao.save(user);
		String userid = user.getId();
		String groupid = user.getGroup();

		Map map = new HashMap();
		map.put("userid", userid);
		map.put("groupid", groupid);
		userdao.saveRelation(map);

	}

	public int saveGroup(Group group) {
		return userdao.saveGroup(group);

	}

}
