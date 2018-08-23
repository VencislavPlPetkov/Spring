package com.ve.springsec.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ve.springsec.model.view.UserRegisterRequestModel;
import com.ve.springsec.service.user.UserService;



@Controller
public class UserController {

	private final UserService userService;
	
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	
	
	@GetMapping("/users/register")
	public ModelAndView register() {
		return new ModelAndView("users/register.html");
	}
	
	

	@PostMapping("/users/register")
    @PreAuthorize("isAnonymous()")
	public ModelAndView register(UserRegisterRequestModel model) {
		
		this.userService.register(model);
		
		return new ModelAndView("redirect:/login");
	}
	
	
	
	
	
	
}











