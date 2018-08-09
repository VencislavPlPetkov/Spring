package com.ve.login.service.user;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ve.login.model.entity.User;
import com.ve.login.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {

		this.userRepository = userRepository;

	}

	@Override
	public boolean register(String username, String password) {

		User user = new User();

		user.setUsername(username);
		user.setPassword(password);

		return this.userRepository.save(user).getId() != null;
	}

}
