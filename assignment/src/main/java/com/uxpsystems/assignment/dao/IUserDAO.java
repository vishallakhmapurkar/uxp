package com.uxpsystems.assignment.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.entity.UserInfo;
@Repository
public interface IUserDAO extends CrudRepository<UserInfo, Long> {
	UserInfo findUserInfoByUserName(String userName);
   
}
