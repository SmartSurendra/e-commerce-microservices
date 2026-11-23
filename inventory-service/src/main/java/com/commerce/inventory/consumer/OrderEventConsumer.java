package com.commerce.inventory.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.commerce.inventory.event.OrderPlacedEvent;
import com.commerce.inventory_service.InventoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderEventConsumer {

	private final InventoryService inventoryService;
	
	@KafkaListener(topics = "", groupId = "inventory-group")
	public void consume(OrderPlacedEvent event) {
		
		
	}
}
