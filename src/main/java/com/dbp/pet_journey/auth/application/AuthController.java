package com.dbp.pet_journey.auth.application;

import com.dbp.pet_journey.auth.domain.AuthService;
import com.dbp.pet_journey.auth.dto.JwtAuthResponse;
import com.dbp.pet_journey.auth.dto.LoginReq;
import com.dbp.pet_journey.auth.dto.RegisterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint para login
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginReq req) {
        System.out.println("Usuario logueado");
        return ResponseEntity.ok(authService.login(req));
    }

    // Endpoint para registro de nuevos usuarios
    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> register(@RequestBody RegisterReq req) {
        return ResponseEntity.ok(authService.register(req));
    }
}
