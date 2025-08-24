package com.smartclinic.controller;

import com.smartclinic.dto.LoginRequest;
import com.smartclinic.dto.LoginResponse;
import com.smartclinic.model.User;
import com.smartclinic.repository.UserRepository;
import com.smartclinic.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserRepository userRepo;
  private final PasswordEncoder encoder;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
    User u = userRepo.findByEmail(req.getEmail()).orElse(null);
    if (u == null || !encoder.matches(req.getPassword(), u.getPasswordHash())) {
      return ResponseEntity.status(401).body("{"error":"Invalid credentials"}");
    }
    return ResponseEntity.ok(new LoginResponse(tokenService.generateToken(u.getEmail())));
  }
}
