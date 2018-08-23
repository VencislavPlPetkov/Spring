package com.ve.springsec.service.user;

import com.ve.springsec.model.entity.User;
import com.ve.springsec.model.view.UserRegisterRequestModel;


public interface UserService {

	User register(UserRegisterRequestModel model);
	
	
}
