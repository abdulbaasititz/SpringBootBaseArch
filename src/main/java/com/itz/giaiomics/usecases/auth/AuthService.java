package com.itz.giaiomics.usecases.auth;


import com.itz.giaiomics.helpers.common.token.ClaimsSet;
import com.itz.giaiomics.helpers.utils.JwtUtil;
import com.itz.giaiomics.models.UserMaster;
import com.itz.giaiomics.usecases.auth.dao.AuthRequestDao;
import com.itz.giaiomics.usecases.auth.dao.AuthTokensDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class AuthService implements UserDetailsService {

    @Autowired
    AuthRepository authRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Value("${spring.crypto.key-path}")
    private String pathName;

    public AuthService() {
    }

    //used in Jwtrequestfilter
    @Override
    public UserDetails loadUserByUsername(String empUserName) throws UsernameNotFoundException {
        UserMaster userInfo = authRepository.findByUserId(empUserName);
        if (userInfo == null) {
            throw new BadCredentialsException("Username wrong");
        }
        return new User(userInfo.getUserId(), userInfo.getPassword(),
                new ArrayList<>());
    }

    public AuthTokensDao createNewTokens(AuthRequestDao authenticationRequest) throws Exception {
        AuthTokensDao authResponseDao;
        UserMaster userInfo;
        String userId = authenticationRequest.getUserId();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userId, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Password Wrong");
        } catch (Exception e) {
            throw new Exception("USERNAME");
        }

        userInfo = authRepository.findByUserId(userId);
        Map<String, Object> claims = ClaimsSet.setClaimsDetails(userInfo);
        String accessToken = jwtTokenUtil.generateAccessToken(userInfo, claims);
        String refreshToken = jwtTokenUtil.generateRefreshToken(userInfo.getUserId(), claims);
        authResponseDao = new AuthTokensDao(accessToken, refreshToken);

        return authResponseDao;
    }

    public Map<String, String> createAccessToken(AuthTokensDao authTokens) throws Exception {
        Map<String, String> accessToken = new HashMap<>();
        try {
            String userName = jwtTokenUtil.getUsernameFromToken(authTokens.getRefreshToken());
            final Claims claims = jwtTokenUtil.getAllClaimsFromToken(authTokens.getRefreshToken());
            accessToken.put("accessToken", jwtTokenUtil.generateAccessToken(userName, claims));

        } catch (ExpiredJwtException e) {
            throw new Exception("Token Expired ,Pls re-login");
        } catch (Exception e) {
            throw new Exception("invalid auth token");
        }
        return accessToken;
    }
}
