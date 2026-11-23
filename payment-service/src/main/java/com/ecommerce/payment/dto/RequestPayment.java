package com.ecommerce.payment.dto;

import java.math.BigDecimal;
import com.ecommerce.payment.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestPayment {

	@NotNull
	private String orderId;
	@Positive
	private BigDecimal amount;
	@NotNull
	private PaymentMethod payment_method;
	
}
