package com.cognizant.Airport.Service;

import com.cognizant.Airport.Check.CheckDetails;
import com.cognizant.Airport.Model.AdminDetails;
import com.cognizant.Airport.Model.ManagerDetails;

public interface IForgotPassword {
	
	public AdminDetails getAdminDetails(int userId);
	public ManagerDetails getManagerDetails(int userId);
	public boolean changePassword(CheckDetails checkDetails, String password);

}
