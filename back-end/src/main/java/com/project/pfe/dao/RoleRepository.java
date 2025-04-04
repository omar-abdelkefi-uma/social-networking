package com.project.pfe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.pfe.entities.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
