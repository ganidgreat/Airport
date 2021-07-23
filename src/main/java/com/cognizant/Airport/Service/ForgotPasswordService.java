package com.cognizant.Airport.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.Airport.Check.CheckDetails;
import com.cognizant.Airport.Model.AdminDetails;
import com.cognizant.Airport.Model.ManagerDetails;

@Service(value="forgotPasswordService")
public class ForgotPasswordService implements IForgotPassword {

	@Autowired
	IForgotPassword forgotPassword;

	@Override
	public AdminDetails getAdminDetails(int userId) {
		// TODO Auto-generated method stub
		return forgotPassword.getAdminDetails(userId);
	}

	@Override
	public ManagerDetails getManagerDetails(int userId) {
		// TODO Auto-generated method stub
		return forgotPassword.getManagerDetails(userId);
	}

	@Override
	public boolean changePassword(CheckDetails checkDetails, String password) {
		// TODO Auto-generated method stub
		return forgotPassword.changePassword(checkDetails, password);
	}

}
