package com.tauanoliveira.cursoMC.services;

import java.util.Random;

import com.tauanoliveira.cursoMC.domain.Cliente;
import com.tauanoliveira.cursoMC.repositories.ClienteRepository;
import com.tauanoliveira.cursoMC.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);//procura email de cliente no repository/banco e coloca em uma var
        if(cliente == null) {
            throw new ObjectNotFoundException("E-mail não encontrado");
        }

        String newPassword = newPassword();//gera senha aleatoria
        cliente.setSenha(bCryptPasswordEncoder.encode(newPassword));//set com a nova senha do cliente incriptografada com bcrypt
        clienteRepository.save(cliente);//salva cliente com a nova senha 
        emailService.sendNewPasswordEmail(cliente, newPassword);//service de email, manda email pro cliente
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i=0;i<10;i++){
            vet[i] = randomChar();//gera um caracter ou numero aleatorio
        }
        return new String(vet);
    }

    private char randomChar() {//gera senha aleatoria de 10 caracteres
        int opt = rand.nextInt(3);//gera numeros de 0 até 2
        if(opt == 0) {//gera digito
            return (char) (rand.nextInt(10) + 48);//gera numero de 0 até 9, olhar unicode-table.com
        }else if(opt == 1) {//gera letra maiuscula 
            return (char) (rand.nextInt(26) + 65);
        }else {//letra minuscula
            return (char) (rand.nextInt(26) + 97);
        }
    }

}
