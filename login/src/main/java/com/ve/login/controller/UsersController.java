package com.ve.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ve.login.model.view.UserCreateRequestModel;
import com.ve.login.service.user.UserService;

@Controller
public class UsersController {

	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users/register")
	public String register(Model model) {

		model.addAttribute("viewModel", new UserCreateRequestModel());

		return "users/register";

	}

	@PostMapping("/users/register")
	public String register(UserCreateRequestModel viewModel, Model model) {

		if (this.userService.register(viewModel.getUsername(), viewModel.getPassword())) {
			return "redirect:/";
		}

		model.addAttribute("viewModel", viewModel);

		return "users/register";

	}

}
