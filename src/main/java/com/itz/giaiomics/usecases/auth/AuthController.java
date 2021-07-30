package com.itz.giaiomics.usecases.auth;


import com.itz.giaiomics.usecases.auth.dao.AuthRequestDao;
import com.itz.giaiomics.usecases.auth.dao.AuthTokensDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${spring.base.path}")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthTokensDao> createNewTokens(@RequestBody AuthRequestDao authenticationRequest) throws Exception {
		return ResponseEntity.ok(authService.createNewTokens(authenticationRequest));
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> createAccessToken(@RequestBody AuthTokensDao authRefresh) throws Exception {
		return ResponseEntity.ok(authService.createAccessToken(authRefresh));
	}

}

