package com.tauanoliveira.cursoMC.config;

import java.text.ParseException;

import com.tauanoliveira.cursoMC.services.DBService;
import com.tauanoliveira.cursoMC.services.EmailService;
import com.tauanoliveira.cursoMC.services.MockEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public Boolean instantiateDatabase() throws ParseException{
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}

