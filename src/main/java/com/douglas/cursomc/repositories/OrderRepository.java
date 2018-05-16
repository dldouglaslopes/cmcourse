package com.douglas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglas.cursomc.domain.PurchaseOrder;

@Repository
public interface OrderRepository extends JpaRepository< PurchaseOrder, Integer>{

}
