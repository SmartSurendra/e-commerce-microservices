package com.commerce.order.order;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String orderNumber;
	private String orderStatus;
	
}
