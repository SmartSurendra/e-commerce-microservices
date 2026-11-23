package com.commerce.inventory_service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.commerce.inventory.dto.InventoryRequest;
import com.commerce.inventory.dto.InventoryResponse;
import com.commerce.inventory.entity.Inventory;
import com.commerce.inventory.entity.ProcessedOrder;
import com.commerce.inventory.event.OrderPlacedEvent;
import com.commerce.inventory.exception.InventoryNotFoundException;
import com.commerce.inventory.mapper.InventoryMapper;
import com.commerce.inventory.repository.InventoryRepository;
import com.commerce.inventory.repository.ProcessedOrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	
		private final InventoryRepository inventoryRepo;
		private final ProcessedOrderRepository processedOrderRepo;
		private final InventoryMapper inventoryMapper;
		
		public InventoryResponse checkInventory(String skuCode) {
			
		Inventory inventory =  inventoryRepo.findBySkuCode(skuCode).orElseThrow(()-> new InventoryNotFoundException(skuCode));
		return inventoryMapper.toresponse(inventory);
		
		}
		
		public void addInventory(InventoryRequest request) {
			Inventory inventory = inventoryMapper.toEntity(request);
			inventoryRepo.save(inventory);
		}
		
		@Transactional
		public void updateStock(OrderPlacedEvent event) {
			if(processedOrderRepo.existsByOrderId(event.getOrderId())) {
				return;
			}
			
		Inventory inventory =  inventoryRepo.findBySkuCode(event.getSkuCode()).orElseThrow(()-> new InventoryNotFoundException(event.getSkuCode()));
		Integer availableQuantity =inventory.getQuantity();
		Integer orderedQuantity = event.getQuantity();
		
		if(availableQuantity<orderedQuantity) {
			throw new RuntimeException("Insufficient stock for this skuCode");
		}
		
		inventory.setQuantity(availableQuantity-orderedQuantity);
		inventoryRepo.save(inventory);
		
		ProcessedOrder processed = new ProcessedOrder();
		processed.setOrderId(event.getOrderId());
		processed.setProcessedAt(LocalDateTime.now());
		processedOrderRepo.save(processed);
		
		}
}