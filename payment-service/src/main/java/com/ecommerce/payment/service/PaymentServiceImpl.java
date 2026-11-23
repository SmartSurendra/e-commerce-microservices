package com.ecommerce.payment.service;

import org.springframework.stereotype.Service;

import com.ecommerce.payment.dto.PaymentResponse;
import com.ecommerce.payment.dto.PaymentStatusUpdateRequest;
import com.ecommerce.payment.dto.RequestPayment;
import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.enums.PaymentStatus;
import com.ecommerce.payment.exception.PaymentNotFoundException;
import com.ecommerce.payment.repository.PaymentRepository;
import com.ecommerce.payment.util.TransactionGenerator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

	private final PaymentRepository paymentRepo;
	
	@Override
	public PaymentResponse createPayment(RequestPayment request) {
		 
		Payment payment = Payment.builder().orderId(request.getOrderId())
		.amount(request.getAmount())
		.paymentMethod(request.getPayment_method())
		.paymentStatus(PaymentStatus.PANDING)
		.transactionId(TransactionGenerator.generate()).build();
		Payment savedPayment = paymentRepo.save(payment);
		return mapToResponse(savedPayment);
	}

	@Override
	public PaymentResponse getPaymentOrderId(String orderId) {
		Payment payment = paymentRepo.findByOrderId(orderId).orElseThrow(()-> new PaymentNotFoundException("payment not found for this ordr id" +orderId));
		return mapToResponse(payment);
	}

	@Override
	public PaymentResponse updatePaymentStatus(Long PaymentId, PaymentStatusUpdateRequest request) {
		Payment payment = paymentRepo.findById(PaymentId).orElseThrow(()-> new PaymentNotFoundException("payment not found with id "+PaymentId));
		payment.setPaymentStatus(request.getPaymentStatus());
		return mapToResponse(payment);
	}
	
	private PaymentResponse mapToResponse(Payment payment) {
		
		return new PaymentResponse(payment.getId(),
				payment.getOrderId(),
				payment.getAmount(),
				payment.getPaymentStatus(),
				payment.getTransactionId());
	}
}
