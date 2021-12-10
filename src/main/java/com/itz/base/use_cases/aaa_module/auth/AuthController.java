package com.itz.base.use_cases.aaa_module.auth;


import com.itz.base.use_cases.aaa_module.auth.dao.AuthRequestDao;
import com.itz.base.use_cases.aaa_module.auth.dao.AuthTokensDao;
import com.itz.base.use_cases.aaa_module.user_master.UserMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Transient;
import java.util.UUID;

@Controller
@RequestMapping("${spring.base.path}" + "/auth")
public class AuthController {
    @Transient
    private final UUID uuid = UUID.randomUUID();
    @Autowired
    private AuthService authService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserMasterService userMasterService;

    @PostMapping("/login")
    public ResponseEntity<AuthTokensDao> createNewTokens(@RequestBody AuthRequestDao authenticationRequest) {
        return ResponseEntity.ok(authService.createNewTokens(authenticationRequest));
    }

    @PostMapping("/web-login")
    public ResponseEntity<AuthTokensDao> createWebNewTokens(@RequestBody AuthRequestDao authenticationRequest) {
        return ResponseEntity.ok(authService.createWebNewTokens(authenticationRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> createAccessToken(@RequestBody AuthTokensDao authRefresh) throws Exception {
        return ResponseEntity.ok(authService.createAccessToken(authRefresh));
    }

    @PostMapping("/web-refresh")
    public ResponseEntity<?> createWebAccessToken(@RequestBody AuthTokensDao authRefresh) throws Exception {
        return ResponseEntity.ok(authService.createWebAccessToken(authRefresh));
    }
}

