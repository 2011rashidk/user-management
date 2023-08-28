package com.happiest.minds.usermanagement.service;

import com.happiest.minds.usermanagement.dto.LoginResponse;
import com.happiest.minds.usermanagement.dto.UserLogin;
import com.happiest.minds.usermanagement.jwtUtility.JwtTokenUtil;
import com.happiest.minds.usermanagement.jwtUtility.JwtUserDetailsService;
import io.jsonwebtoken.impl.DefaultClaims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtAuthenticationService {
    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtUserDetailsService userDetailsService;

    public LoginResponse createAuthenticationToken(UserLogin userLogin) throws Exception {
        authenticate(userLogin.getUsername(), userLogin.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(userLogin.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new LoginResponse(userDetails.getUsername(), token);
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public String refreshToken(HttpServletRequest request) {
        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");
        Map<String, Object> expectedMap = getMapFromIoJsonWebTokenClaims(claims);
        return jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
    }

    public Map<String, Object> getMapFromIoJsonWebTokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

}
