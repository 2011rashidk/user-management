package com.happiest.minds.usermanagement.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.happiest.minds.usermanagement.entity.Role;
import com.happiest.minds.usermanagement.entity.Token;
import com.happiest.minds.usermanagement.entity.User;
import com.happiest.minds.usermanagement.exception.NotFoundException;
import com.happiest.minds.usermanagement.repository.RoleRepository;
import com.happiest.minds.usermanagement.repository.TokenRepository;
import com.happiest.minds.usermanagement.repository.UserRepository;
import com.happiest.minds.usermanagement.request.LoginDTO;
import com.happiest.minds.usermanagement.request.UserDTO;
import com.happiest.minds.usermanagement.response.LoginResponse;
import com.happiest.minds.usermanagement.security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static com.happiest.minds.usermanagement.enums.Constants.*;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserService userService;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepository roleRepository;

    public LoginResponse register(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleRepository.getRoleByRoleName("USER");
        user.setRoles(Collections.singleton(role));
        user = userService.createUser(user);
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        LocalDateTime accessTokenExpiryTime = jwtService.extractExpiration(accessToken).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime refreshTokenExpiry = jwtService.extractExpiration(refreshToken).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        saveUserToken(user, accessToken);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiry(accessTokenExpiryTime)
                .refreshTokenExpiry(refreshTokenExpiry)
                .build();
    }

    public LoginResponse authenticate(LoginDTO loginDTO) {
        User user = userService.findByUsername(loginDTO.getUsername());
        if (user != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            String accessToken = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            LocalDateTime accessTokenExpiry = jwtService.extractExpiration(accessToken).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime refreshTokenExpiry = jwtService.extractExpiration(refreshToken).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            revokeAllUserTokens(user);
            saveUserToken(user, accessToken);
            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .accessTokenExpiry(accessTokenExpiry)
                    .refreshTokenExpiry(refreshTokenExpiry)
                    .build();
        }
        throw new NotFoundException(USERNAME_NOT_FOUND.getValue());
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .jwtToken(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = userService.findByUsername(userEmail);
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                LocalDateTime accessTokenExpiry = jwtService.extractExpiration(accessToken).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime refreshTokenExpiry = jwtService.extractExpiration(refreshToken).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                LoginResponse loginResponse = LoginResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .accessTokenExpiry(accessTokenExpiry)
                        .refreshTokenExpiry(refreshTokenExpiry)
                        .build();
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                mapper.writeValue(response.getOutputStream(), loginResponse);
            }
        }
    }

}
