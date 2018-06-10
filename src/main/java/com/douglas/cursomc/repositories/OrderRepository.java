package com.douglas.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.cursomc.domain.Client;
import com.douglas.cursomc.domain.PurchaseOrder;

@Repository
public interface OrderRepository extends JpaRepository< PurchaseOrder, Integer>{

	@Transactional(readOnly = true)
	Page<PurchaseOrder> findByClient(Client client, Pageable pageRequest);
}
