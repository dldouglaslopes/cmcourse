package com.douglas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglas.cursomc.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository< Payment, Integer>{

}
