package com.tauanoliveira.cursoMC.repositories;

import com.tauanoliveira.cursoMC.domain.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
}