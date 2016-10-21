package com.hanbit.team05.core.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityService {

	private static PasswordEncoder encoder = new BCryptPasswordEncoder(4);
}
