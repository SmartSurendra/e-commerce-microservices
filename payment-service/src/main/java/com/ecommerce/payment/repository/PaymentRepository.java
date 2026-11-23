package com.ecommerce.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ecommerce.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

	@Query()
	Optional<Payment> findByOrderId(String orderId);
	
	Optional<Payment> findByTransactionId(String transactionId);
	
}

