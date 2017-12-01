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
		return (List<UserInfo>) IUserDAO.findAll();
	}

	

	public UserInfo putUser(UserInfo userInfo) {
		return postUser(userInfo);
		
	}

	public UserInfo postUser(UserInfo userInfo) {
		UserInfo res=null;
		UserInfo exsitingUser = findUserInfoByUserName(userInfo.getUserName());
	    if(exsitingUser!=null) {
	    	exsitingUser.setPassword(userInfo.getPassword());
	    	exsitingUser.setRole(userInfo.getRole());
	    	exsitingUser.setStatus(userInfo.getStatus());
	    	res= IUserDAO.save(exsitingUser);
	    	
	    }else {
	    	res= IUserDAO.save(userInfo);
	    }
		return res;
	}

	public void deleteUser(Long id) {
		IUserDAO.delete(id);

	}

	public UserInfo getUserById(Long id) {
	
		return IUserDAO.findOne(id);
	}



	@Override
	public UserInfo findUserInfoByUserName(String userName) {
		UserInfo user =IUserDAO.findUserInfoByUserName(userName);
		return user;
	}



	@Override
	public UserInfo postDummyUser(UserInfo userInfo) {
		return postUser(userInfo);
	}

}
