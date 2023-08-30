package com.happiest.minds.usermanagement.service;

import com.happiest.minds.usermanagement.response.LoginResponse;
import com.happiest.minds.usermanagement.request.UserLogin;
import com.happiest.minds.usermanagement.security.JwtTokenUtil;
import com.happiest.minds.usermanagement.security.JwtUserDetailsService;
import io.jsonwebtoken.impl.DefaultClaims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
        Date expirationDateFromToken = jwtTokenUtil.getExpirationDateFromToken(token);
        LocalDateTime expiryDateTime = LocalDateTime.ofInstant(expirationDateFromToken.toInstant(),
                ZoneId.systemDefault());
        return new LoginResponse(userDetails.getUsername(), token, expiryDateTime);
    }

    public void authenticate(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }

    public String refreshToken(HttpServletRequest request) {
        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");
        Map<String, Object> expectedMap = getMapFromIoJsonWebTokenClaims(claims);
        return jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
    }

    public Map<String, Object> getMapFromIoJsonWebTokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

}
