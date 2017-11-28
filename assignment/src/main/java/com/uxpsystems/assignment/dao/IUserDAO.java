package com.uxpsystems.assignment.dao;

import java.util.List;

import com.uxpsystems.assignment.entity.UserInfo;

public interface IUserDAO {
     List<UserInfo> getAllUsers();
	 UserInfo getUserById(Long id);
     void putUser(UserInfo userInfo);
	 void postUser(UserInfo userInfo);
     void deleteUser(Long id);
}
