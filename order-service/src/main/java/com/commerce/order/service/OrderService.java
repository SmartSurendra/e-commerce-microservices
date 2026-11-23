package com.commerce.order.service;

import com.commerce.order.dto.OrderRequest;
import com.commerce.order.order.OrderResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface OrderService {

	OrderResponse PlaceOrder(OrderRequest orderRequest, HttpServletRequest request);
}
