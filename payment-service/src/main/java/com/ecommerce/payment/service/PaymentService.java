package com.ecommerce.payment.service;

import com.ecommerce.payment.dto.PaymentResponse;
import com.ecommerce.payment.dto.PaymentStatusUpdateRequest;
import com.ecommerce.payment.dto.RequestPayment;

public interface PaymentService {

	// these are abstract method inside interface by default
	
	PaymentResponse createPayment(RequestPayment request); 
	PaymentResponse getPaymentOrderId(String orderId);
	PaymentResponse updatePaymentStatus(Long PaymentId, PaymentStatusUpdateRequest request);
}
