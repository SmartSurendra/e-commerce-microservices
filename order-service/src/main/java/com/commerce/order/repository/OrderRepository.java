package com.commerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commerce.order.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	
	
}
