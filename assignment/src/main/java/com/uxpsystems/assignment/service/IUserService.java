package com.uxpsystems.assignment.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.uxpsystems.assignment.entity.UserInfo;



public interface IUserService {
	 @Secured ({"ROLE_ADMIN"})
     List<UserInfo> getAllUsers();
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	 UserInfo getUserById(Long id);
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	 UserInfo putUser(UserInfo userInfo);
	 @Secured ({"ROLE_ADMIN"})
	 UserInfo postUser(UserInfo userInfo);
	@Secured ({"ROLE_ADMIN"})
     void deleteUser(Long id);
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	UserInfo findUserInfoByUserName(String userName);
	 UserInfo postDummyUser(UserInfo userInfo);
}
