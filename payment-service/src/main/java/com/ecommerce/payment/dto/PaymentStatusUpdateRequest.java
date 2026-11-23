package com.ecommerce.payment.dto;

import com.ecommerce.payment.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusUpdateRequest {

	@NotNull
	private PaymentStatus paymentStatus;
	
}
