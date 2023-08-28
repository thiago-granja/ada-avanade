package com.avanade.avanade.controller;

import com.avanade.avanade.dto.LoginDTO;
import com.avanade.avanade.dto.TokenDTO;
import com.avanade.avanade.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    private TokenDTO logar(@RequestBody LoginDTO body){
        return this.loginService.logar(body);
    }
}
