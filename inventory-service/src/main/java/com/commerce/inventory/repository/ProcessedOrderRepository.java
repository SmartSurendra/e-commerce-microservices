package com.commerce.inventory.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commerce.inventory.entity.ProcessedOrder;

@Repository
public interface ProcessedOrderRepository extends JpaRepository<ProcessedOrder, Long>{
	boolean existsByOrderId(String orderId);
}
