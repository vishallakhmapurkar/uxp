package com.uxpsystems.assignment.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxpsystems.assignment.entity.UserInfo;
@Transactional
@Repository
public class UserDAOImpl implements IUserDAO {
	 @PersistenceContext
	 private EntityManager entityManager;
	public List<UserInfo> getAllUsers() {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(UserInfo.class);
		return criteria.list();
	}

	

	public void putUser(UserInfo userInfo) {
		UserInfo userInfo1 = getUserById(userInfo.getId());
		userInfo1.setStatus(userInfo.getStatus());
		entityManager.merge(userInfo1);

	}

	public void postUser(UserInfo userInfo) {
		 entityManager.persist(userInfo);

	}

	public void deleteUser(Long id) {
		UserInfo userInfo = getUserById(id);
		 if (userInfo != null) {
		 entityManager.remove(userInfo);
		 }

	}

	public UserInfo getUserById(Long id) {
		return entityManager.find(UserInfo.class, id);
	}



	
	
}
