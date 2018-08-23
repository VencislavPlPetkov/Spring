package com.ve.springsec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ve.springsec.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findFirstByUsername(String username);
	
}
