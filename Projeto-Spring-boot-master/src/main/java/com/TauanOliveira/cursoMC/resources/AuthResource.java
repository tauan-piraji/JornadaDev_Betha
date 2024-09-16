package com.tauanoliveira.cursoMC.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.tauanoliveira.cursoMC.dto.EmailDTO;
import com.tauanoliveira.cursoMC.segurity.JWTUtil;
import com.tauanoliveira.cursoMC.segurity.UserSS;
import com.tauanoliveira.cursoMC.services.AuthService;
import com.tauanoliveira.cursoMC.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil; 

    @Autowired
    private AuthService authService; //gerador de senha

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();//busca usuario logado
        String token = jwtUtil.generateToken(user.getUsername());//gerar token com mesmo usuario
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)//endpoint (/forgot) metodo POST 
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO) {
        authService.sendPassword(objDTO.getEmail());
        return ResponseEntity.noContent().build();
    }
}
