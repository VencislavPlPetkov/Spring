package com.ve.springsec.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestSecurityController {

	@GetMapping("/unauth")
	public @ResponseBody String unAuthorizedMethod() {
		
		return "Unauthorized method called";
	}
	
	
	

	@GetMapping("/auth")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody String authorizedMethod(Principal principal) {
        String username = principal.getName();
        return "I am AUTHORIZED method";
    }
	
	
	
	 @GetMapping("/for_users")
    @PreAuthorize("hasRole('USER')")
    public @ResponseBody String users() {
        return "I am AUTHORIZED method FOR USERS";
    }

	 
	 
    @GetMapping("/for_admins")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody String admins() {
        return "I am AUTHORIZED method FOR ADMINS";
    }
	
	
	
	
	
	
}























