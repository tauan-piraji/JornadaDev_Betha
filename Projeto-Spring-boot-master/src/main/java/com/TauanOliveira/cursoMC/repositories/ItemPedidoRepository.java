package com.tauanoliveira.cursoMC.repositories;

import com.tauanoliveira.cursoMC.domain.ItemPedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{
}