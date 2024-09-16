package com.tauanoliveira.cursoMC.repositories;

import com.tauanoliveira.cursoMC.domain.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
}