package com.cognizant.Airport.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.Airport.Check.CheckDetails;
import com.cognizant.Airport.Model.AdminDetails;
import com.cognizant.Airport.Model.ManagerDetails;
import com.cognizant.Airport.Service.IForgotPassword;

@Component
public class ForgotPasswordDao implements IForgotPassword {

	static Logger logger = Logger.getLogger(ForgotPasswordDao.class);

	public ForgotPasswordDao() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	public EntityManager entityManager;

	@Override
	public AdminDetails getAdminDetails(int userId) {
		// TODO Auto-generated method stub
		return entityManager.find(AdminDetails.class, userId);
	}

	@Override
	public ManagerDetails getManagerDetails(int userId) {
		// TODO Auto-generated method stub
		return entityManager.find(ManagerDetails.class, userId);
	}

	@Transactional
	@Override
	public boolean changePassword(CheckDetails checkDetails, String password) {
		// TODO Auto-generated method stub
		if (checkDetails.getUserType().equals("Admin")) {
			AdminDetails adminDetails = checkDetails.getAdminDetails();
			AdminDetails adminDetails2 = entityManager.find(AdminDetails.class, adminDetails.getAdminId());
			adminDetails2.setPassword(password);
			entityManager.persist(adminDetails2);
			return true;
		} else if (checkDetails.getUserType().equals("Manager")) {
			ManagerDetails managerDetails = checkDetails.getManagerDetails();
			ManagerDetails managerDetails2 = entityManager.find(ManagerDetails.class, managerDetails.getManagerId());
			managerDetails2.setPassword(password);
			entityManager.persist(managerDetails2);
			return true;
		}
		return false;
	}

}
