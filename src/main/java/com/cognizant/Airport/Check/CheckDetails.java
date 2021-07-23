package com.cognizant.Airport.Check;

import com.cognizant.Airport.Model.AdminDetails;
import com.cognizant.Airport.Model.ManagerDetails;

public class CheckDetails {

	private AdminDetails adminDetails;
	private ManagerDetails managerDetails;
	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public AdminDetails getAdminDetails() {
		return adminDetails;
	}

	public void setAdminDetails(AdminDetails adminDetails) {
		this.adminDetails = adminDetails;
	}

	public ManagerDetails getManagerDetails() {
		return managerDetails;
	}

	public void setManagerDetails(ManagerDetails managerDetails) {
		this.managerDetails = managerDetails;
	}

	public CheckDetails(ManagerDetails managerDetails, String userType) {
		super();
		this.managerDetails = managerDetails;
		this.userType = userType;
	}

	public CheckDetails(AdminDetails adminDetails, String userType) {
		super();
		this.adminDetails = adminDetails;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "CheckDetails [adminDetails=" + adminDetails + ", managerDetails=" + managerDetails + ", userType="
				+ userType + "]";
	}

}
