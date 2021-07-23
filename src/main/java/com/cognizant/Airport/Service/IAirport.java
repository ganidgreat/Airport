package com.cognizant.Airport.Service;

import java.util.List;

import com.cognizant.Airport.Model.AdminDetails;
import com.cognizant.Airport.Model.LoginDetails;
import com.cognizant.Airport.Model.ManagerDetails;
import com.cognizant.Airport.Model.SecurityQuestion;

public interface IAirport {
	
	public AdminDetails adminreg(AdminDetails ad, String securityQuestion);

	public List<AdminDetails> adminreqList();

	public List<ManagerDetails> managerreqList();

	public void adminapproverequest(int id);

	public void managerapproverequest(int id);

	public String adminLogin(LoginDetails loginDetails);

	public AdminDetails getAdminDetails(int userId);

	public SecurityQuestion getSecurityQuestionById(int questionId);
	
}
