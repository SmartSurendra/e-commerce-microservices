package com.commerce.order.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.commerce.order.dto.InventoryResponse;
import org.springframework.stereotype.Service;

import com.commerce.order.client.InventoryFeignClients;
import com.commerce.order.dto.OrderRequest;
import com.commerce.order.event.OrderPlacedEvent;
import com.commerce.order.kafka.OrderEventProducer;
import com.commerce.order.mapper.OrderMapper;
import com.commerce.order.order.Order;
import com.commerce.order.order.OrderResponse;
import com.commerce.order.repository.OrderRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepo;
    private final OrderMapper orderMapper;
    private final OrderEventProducer orderEventProducer;
    
    // declaration of feign client 
    private final InventoryFeignClients inventoryFeignClients;
    

	public OrderResponse PlaceOrder(OrderRequest request, HttpServletRequest requestHeader) {
		
		String auth = requestHeader.getHeader("Authorization");
		
		// rest call to check product availability  into inventory service 
		InventoryResponse inventory = inventoryFeignClients.isInStock(request.getSkuCode(), requestHeader);
		
		if(!inventory.isInStock()) {
			
			throw new RuntimeException("Product is out of stock");
		}
		
		Order order = orderMapper.toEntity(request);
		String orderId = UUID.randomUUID().toString();
		order.setOrderNumber(orderId);
		order.setOrderStatus("CREATED");
		orderRepo.save(order);
		
		OrderPlacedEvent event = new OrderPlacedEvent();
		event.setEventId(orderId);
        event.setOrderId(orderId);
        
        event.setSkuCode(request.getSkuCode());
        event.setQuantity(request.getQuantity());
        event.setEventTime(LocalDateTime.now());
        orderEventProducer.sendOrderEvent(event);
        
		return orderMapper.toResponse(order);
	}	
}
