package com.happiest.minds.usermanagement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

  private String accessToken;
  private String refreshToken;
  LocalDateTime tokenExpiry;

}
