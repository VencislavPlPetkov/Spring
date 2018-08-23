package com.ve.springsec.service.user;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ve.springsec.model.entity.Role;
import com.ve.springsec.model.entity.User;
import com.ve.springsec.model.view.UserRegisterRequestModel;
import com.ve.springsec.repository.RoleRepository;
import com.ve.springsec.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService{

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private PasswordEncoder BCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			RoleRepository roleRepository,
			PasswordEncoder BCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.BCryptPasswordEncoder = BCryptPasswordEncoder;
	}
	
	
	
	
	@Override
	public User register(UserRegisterRequestModel model) {

		User user = new User();
		user.setUsername(model.getUsername());
		user.setPassword(this.BCryptPasswordEncoder.encode(model.getPassword()));
		
		Role role = this.roleRepository.findFirstByName("USER");
		role.getUsers().add(user);
		user.getRoles().add(role);
		
		this.roleRepository.save(role);
		
		return this.userRepository.save(user);
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findFirstByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		
		
//		Set<GrantedAuthority> roles = user.getRoles()
//                .stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
//                .collect(Collectors.toSet());
		
		
		 Set<GrantedAuthority> roles = new HashSet<>();
		    for (Role role : user.getRoles()){
		    	roles.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		    }
		
		
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );

		
		return userDetails;
	}
	
	
	
	
}
























