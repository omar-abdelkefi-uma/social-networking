package com.project.pfe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pfe.entities.User;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	boolean existsByEmail(String email);

	// to modify a record in db add two annotations
	@Transactional
	@Modifying
	@Query("UPDATE User p SET p.password = :password WHERE p.id = :id")
	void updatePassword(@Param("password") String password, @Param("id") Long id);

}
