package com.douglas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglas.cursomc.domain.ItemOrder;

@Repository
public interface ItemOrderRepository extends JpaRepository< ItemOrder, Integer>{

}
