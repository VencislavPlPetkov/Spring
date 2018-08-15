package com.ve.codeinterceptor.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ve.codeinterceptor.model.TestCaptchaBindingModel;

@org.springframework.stereotype.Controller
public class HomeController {

	@GetMapping("/")
	public String getHome(Model model) {

		model.addAttribute("title", "Code Interceptor");

		return "home";

	}

	@PostMapping("/secured")
	public String secured(TestCaptchaBindingModel bindingModel, 
			Model model) {

		model.addAttribute("username", bindingModel.getUsername());
		
		return "secured";
	}

}
