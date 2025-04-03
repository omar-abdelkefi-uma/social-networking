package com.project.pfe.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.pfe.entities.PasswordResetToken;

import jakarta.transaction.Transactional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	@Transactional
	Long deleteByUser_id(Long id);

	PasswordResetToken findByToken(String token);

}