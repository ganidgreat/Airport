package com.cognizant.Airport.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.Airport.Check.CheckDetails;
import com.cognizant.Airport.Model.AdminDetails;
import com.cognizant.Airport.Model.LoginDetails;
import com.cognizant.Airport.Model.ManagerDetails;
import com.cognizant.Airport.Service.IAirport;
import com.cognizant.Airport.Service.IForgotPassword;
import com.cognizant.Airport.Service.IManagerDetailsService;

@Controller
public class LoginControl {
	@Resource(name = "managerDetailsService")
	IManagerDetailsService managerDetailsService;

	@Resource(name = "adminService")
	IAirport airportService;

	@Resource(name = "forgotPasswordService")
	IForgotPassword forgotPassword;

	ModelAndView modelAndView;

	CheckDetails checkDetails;

	@RequestMapping(value = "/login")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/Register")
	public String register(String submit) {
		if (submit.equals("Register_Manager"))
			return "RegisterManager";
		else
			return "RegisterAdmin";
	}

	@RequestMapping(value = "/AdminRegister", method = RequestMethod.POST)
	public ModelAndView adminreg(AdminDetails ad, String securityQuestion1) // for storing admin details in the database
	{
		ModelAndView modelAndView = new ModelAndView("Success");
		AdminDetails adminDetails = airportService.adminreg(ad, securityQuestion1);
		modelAndView.addObject("Message", adminDetails.getAdminId());
		return modelAndView;
	}

	@RequestMapping(value = "/ManagerRegister", method = RequestMethod.POST)
	public ModelAndView RegisterManager(ManagerDetails managerDetails, String securityQuestion1) {
		ModelAndView modelAndView = new ModelAndView("Success");
		ManagerDetails md = managerDetailsService.saveManagerDetails(managerDetails, securityQuestion1);
		modelAndView.addObject("Message", md.getManagerId());
		return modelAndView;

	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ModelAndView UserLogin(String Profile, Integer userId, String password, HttpServletRequest request,
			HttpServletResponse response) {
		LoginDetails.setProfile(Profile);
		LoginDetails.setUserId(userId);
		LoginDetails.setPassword(password);
		HttpSession session = request.getSession();

		if (LoginDetails.getProfile().equalsIgnoreCase("manager")) {
			String s = managerDetailsService.managerLogin(LoginDetails.getLoginDetails());
			if (s.equals("success")) {
				modelAndView = new ModelAndView("ManagerAirport");
				ManagerDetails md = managerDetailsService.getManagerDetails(LoginDetails.getUserId());
				modelAndView.addObject("Manager", md);
				session.setAttribute("Name", md.getFirstName());
			} else {
				modelAndView = new ModelAndView("index");
				modelAndView.addObject("error", s);
			}
		} else {
			String s = airportService.adminLogin(LoginDetails.getLoginDetails());
			if (s.equals("success")) {
				modelAndView = new ModelAndView("AdminAirport");
				AdminDetails ad = airportService.getAdminDetails(LoginDetails.getUserId());
				modelAndView.addObject("Admin", ad);
				session.setAttribute("Name", ad.getFirstName());
			} else {
				modelAndView = new ModelAndView("index");
				modelAndView.addObject("error", s);
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public ModelAndView home() {
		if (LoginDetails.getProfile().equalsIgnoreCase("manager")) {
			String s = managerDetailsService.managerLogin(LoginDetails.getLoginDetails());
			if (s.equals("success")) {
				modelAndView = new ModelAndView("ManagerAirport");
				ManagerDetails md = managerDetailsService.getManagerDetails(LoginDetails.getUserId());
				modelAndView.addObject("Manager", md);

			} else {
				modelAndView = new ModelAndView("index");
				modelAndView.addObject("error", s);
			}
		} else {
			String s = airportService.adminLogin(LoginDetails.getLoginDetails());
			if (s.equals("success")) {
				modelAndView = new ModelAndView("AdminAirport");
				AdminDetails ad = airportService.getAdminDetails(LoginDetails.getUserId());
				modelAndView.addObject("Admin", ad);

			} else {
				modelAndView = new ModelAndView("index");
				modelAndView.addObject("error", s);
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String logout(ModelAndView modelAndView, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/app/login";
	}

	@RequestMapping(value = "/error", method = RequestMethod.POST)
	public ModelAndView error(ModelAndView modelAndView, HttpServletRequest request) {
		modelAndView = new ModelAndView("index");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		modelAndView.addObject("error", "SOME ERROR OCCURED PLEASE LOGIN AGAIN");
		return modelAndView;
	}

	@RequestMapping(value = "/forgotPassword1", method = RequestMethod.POST)
	public ModelAndView forgotPassword(ModelAndView modelAndView, String userId, String userType) {
		modelAndView = new ModelAndView("resetPassword");
		if (userType.equals("Admin")) {
			AdminDetails adminDetails = forgotPassword.getAdminDetails(Integer.parseInt(userId));
			if (adminDetails != null) {
				modelAndView.addObject("userDetails", adminDetails);
				String securityQuestion = adminDetails.getSecurityQuestion().getQuestion();
				modelAndView.addObject("securityQuestion", securityQuestion);
				modelAndView.addObject("userType", userType);
				checkDetails = new CheckDetails(adminDetails, userType);
				modelAndView.addObject(checkDetails);
			} else {
				modelAndView = new ModelAndView("forgotPassword");
				modelAndView.addObject("message", "Invalid user ID, Please try again!!!");
			}
		} else if (userType.equals("Manager")) {
			ManagerDetails managerDetails = forgotPassword.getManagerDetails(Integer.parseInt(userId));
			if (managerDetails != null) {
				modelAndView.addObject("userDetails", managerDetails);
				String securityQuestion = managerDetails.getSecurityQuestion().getQuestion();
				modelAndView.addObject("securityQuestion", securityQuestion);
				modelAndView.addObject("userType", userType);
				checkDetails = new CheckDetails(managerDetails, userType);
				modelAndView.addObject(checkDetails);
			} else {
				modelAndView = new ModelAndView("forgotPassword");
				modelAndView.addObject("message", "Invalid user ID, Please try again!!!");
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/fp", method = RequestMethod.GET)
	public ModelAndView fp(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("forgotPassword");
		return modelAndView;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPassword(ModelAndView modelAndView, String givenAnswer) {
		boolean bool = false;
		if (checkDetails.getUserType().equals("Admin")) {
			if (checkDetails.getAdminDetails().getSecurityAnswer().equals(givenAnswer))
				bool = true;
		} else if (checkDetails.getUserType().equals("Manager")) {
			if (checkDetails.getManagerDetails().getSecurityAnswer().equals(givenAnswer))
				bool = true;

		}
		if (bool)
			modelAndView = new ModelAndView("resetPasswordConfirmation");
		else {
			modelAndView = new ModelAndView("resetPassword");
			if (checkDetails.getUserType().equals("Admin")) {
				modelAndView.addObject("userDetails", checkDetails.getAdminDetails());
				String securityQuestion = checkDetails.getAdminDetails().getSecurityQuestion().getQuestion();
				modelAndView.addObject("securityQuestion", securityQuestion);
			} else {
				modelAndView.addObject("userDetails", checkDetails.getManagerDetails());
				String securityQuestion = checkDetails.getManagerDetails().getSecurityQuestion().getQuestion();
				modelAndView.addObject("securityQuestion", securityQuestion);
			}
			modelAndView.addObject("faliedMessage", "Invalid answer");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(String password1) {
		forgotPassword.changePassword(checkDetails, password1);
		checkDetails = null;
		return "redirect:/app/login";
	}
}
