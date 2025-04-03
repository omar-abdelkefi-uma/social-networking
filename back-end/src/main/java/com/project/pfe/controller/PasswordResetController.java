package com.project.pfe.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.pfe.dao.PasswordResetTokenRepository;
import com.project.pfe.dao.UserRepository;
import com.project.pfe.dto.PasswordResetDto;
import com.project.pfe.entities.PasswordResetToken;
import com.project.pfe.entities.User;

@RestController
@RequestMapping("/new-password")
public class PasswordResetController {

	@Autowired
	private UserRepository userdao;

	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@ModelAttribute("passwordResetForm")
	public PasswordResetDto passwordReset() {
		return new PasswordResetDto();
	}

	@GetMapping("/{token}")
	public String displayResetPasswordPage(@PathVariable String token) {
		PasswordResetToken resetToken = tokenRepository.findByToken(token);
		if (resetToken == null) {
			// TODO log "Could not find password reset token.");
			return "/login/ce lien est expiré";
		} else if (resetToken.isExpired()) {

			// TODO log "Token has expired, please request a new password reset.");
			return "/login/ce lien est expiré";
		} else {
			// TODO log resetToken.getToken());
			return "/ok";
		}

	}

	@PutMapping()
	@Transactional
	public void handlePasswordReset(@RequestBody Map object, BindingResult result,
			RedirectAttributes redirectAttributes, Authentication auth) {

		String password = object.get("password").toString();
		String token = object.get("token").toString();
		PasswordResetToken resetToken = tokenRepository.findByToken(token);
		String updatedPassword = passwordEncoder.encode(password);

		userdao.updatePassword(updatedPassword, resetToken.getUser().getId());
		// delete old token in database
		tokenRepository.deleteByUser_id(resetToken.getUser().getId());

	}

}
