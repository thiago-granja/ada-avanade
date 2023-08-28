package com.avanade.avanade.service;

import com.avanade.avanade.dto.LoginDTO;
import com.avanade.avanade.dto.TokenDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private AuthenticationManager authManager;
    private JwtService jwtService;

    public LoginService(AuthenticationManager authManager, JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public TokenDTO logar(LoginDTO loginDTO) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.username(), loginDTO.password());
        authManager.authenticate(authenticationToken);
        String token = jwtService.getToken(loginDTO.username());

        return new TokenDTO(token);
    }
}
