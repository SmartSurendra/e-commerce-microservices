package com.commerce.inventory.mapper;

import com.commerce.inventory.dto.InventoryRequest;
import com.commerce.inventory.dto.InventoryResponse;
import com.commerce.inventory.entity.Inventory;

public class InventoryMapper {

	public Inventory toEntity(InventoryRequest request) {
		
		Inventory inventory = new Inventory();
		inventory.setSkuCode(request.getSkuCode());
		inventory.setQuantity(request.getQuantity());
		return inventory;
		
		}
		public InventoryResponse toresponse(Inventory inventory) {
			return new InventoryResponse(
					inventory.getSkuCode(),
					inventory.getQuantity()>0,
					inventory.getQuantity());
		}
}
