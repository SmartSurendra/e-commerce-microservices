package com.commerce.order.client;

import com.commerce.order.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletRequest;

@FeignClient(name = "/inventory-service")
public interface InventoryFeignClients {
	
	@GetMapping("/api/inventory/{skuCode}")
    InventoryResponse isInStock(@PathVariable String skuCode, HttpServletRequest requestHeader);
 }
