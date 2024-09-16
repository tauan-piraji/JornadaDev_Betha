package com.tauanoliveira.cursoMC.services;

import com.tauanoliveira.cursoMC.segurity.UserSS;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    
    public static UserSS authenticated() {
        try{
        return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//retorna usuario logado
        }catch(Exception e){
            return null;
        }
    }
}
