package com.cognizant.Airport.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.cognizant.Airport.Model.LoginDetails;
import com.cognizant.Airport.Model.ManagerDetails;
import com.cognizant.Airport.Model.SecurityQuestion;
import com.cognizant.Airport.Service.IManagerDetailsService;

@Repository
public class ManagerDao implements IManagerDetailsService {
	@PersistenceContext
	public EntityManager entityManager;

	@Override
	public ManagerDetails saveManagerDetails(ManagerDetails managerDetails, String securityQuestion) {
		SecurityQuestion securityQuestion2 = entityManager.find(SecurityQuestion.class,
				Integer.parseInt(securityQuestion));
		managerDetails.setSecurityQuestion(securityQuestion2);
		ManagerDetails managerDetail = entityManager.merge(managerDetails);
		return managerDetail;
	}

	@Override
	public String managerLogin(LoginDetails loginDetails) {
		ManagerDetails managerDetails = entityManager.find(ManagerDetails.class, LoginDetails.getUserId());
		if (managerDetails != null) {
			if (managerDetails.getStatus() == 0) {
				return "Account Not Activated.";
			}

			if (managerDetails.getPassword().equals(LoginDetails.getPassword())) {
				return "success";
			} else {
				return ("Wrong Password");
			}
		}

		else {
			return "Wrong UserId";
		}
	}

	@Override
	public ManagerDetails getManagerDetails(int i) {
		// TODO Auto-generated method stub
		return entityManager.find(ManagerDetails.class, i);
	}
}
