package com.uxpsystems.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.IUserDAO;
import com.uxpsystems.assignment.entity.UserInfo;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDAO IUserDAO;
	public List<UserInfo> getAllUsers() {
		return IUserDAO.getAllUsers();
	}

	

	public void putUser(UserInfo userInfo) {
		IUserDAO.putUser(userInfo);
		
	}

	public boolean postUser(UserInfo userInfo) {
	
	    	   IUserDAO.postUser(userInfo);
	    	   return true;
	}

	public void deleteUser(Long id) {
		IUserDAO.deleteUser(id);

	}

	public UserInfo getUserById(Long id) {
	
		return IUserDAO.getUserById(id);
	}

}
