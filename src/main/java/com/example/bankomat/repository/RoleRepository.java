package com.example.bankomat.repository;

import com.example.bankomat.entity.Role;
import com.example.bankomat.entity.enums.RoleNames;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleNames roleName);
}
