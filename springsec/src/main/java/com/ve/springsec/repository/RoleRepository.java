package com.ve.springsec.repository;

import com.ve.springsec.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findFirstByName(String name);

}
