package com.commerce.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.commerce.order.dto.OrderRequest;
import com.commerce.order.order.OrderResponse;
import com.commerce.order.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class OrderController {

	private OrderService orderService;
	
	public ResponseEntity<OrderResponse> placeOrder(@RequestBody @Valid OrderRequest eorderRquest, HttpServletRequest request){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.PlaceOrder(eorderRquest, request));
	}
	
}
