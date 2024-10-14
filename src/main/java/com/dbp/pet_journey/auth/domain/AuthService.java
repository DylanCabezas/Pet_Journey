package com.dbp.pet_journey.auth.domain;

import com.dbp.pet_journey.auth.dto.JwtAuthResponse;
import com.dbp.pet_journey.auth.dto.LoginReq;
import com.dbp.pet_journey.auth.dto.RegisterReq;
import com.dbp.pet_journey.auth.exceptions.UserAlreadyExistException;
import com.dbp.pet_journey.auth.infraestructure.UserAccountRepository;
import com.dbp.pet_journey.auth.utils.AuthorizationUtils;
import com.dbp.pet_journey.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthorizationUtils authorizationUtils;

    public JwtAuthResponse login(LoginReq loginReq) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword())
        );

        UserDetails userDetails = authorizationUtils.loadUserByUsername(loginReq.getUsername());
        String token = jwtService.generateToken(userDetails);
        return new JwtAuthResponse(token);
    }

    public JwtAuthResponse register(RegisterReq registerReq) {
        if (userAccountRepository.existsByUsername(registerReq.getUsername())) {
            throw new UserAlreadyExistException("El nombre de usuario ya existe");
        }

        UserAccount user = new UserAccount();
        user.setUsername(registerReq.getUsername());
        user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        user.setRoles(registerReq.getRoles());

        userAccountRepository.save(user);

        UserDetails userDetails = authorizationUtils.loadUserByUsername(registerReq.getUsername());
        String token = jwtService.generateToken(userDetails);

        return new JwtAuthResponse(token);
    }
}
