package luisa.almeida.restaurant_api_lca.data_transfer;


import org.springframework.stereotype.Component;

import luisa.almeida.restaurant_api_lca.dto.MenuDto;
import luisa.almeida.restaurant_api_lca.dto.OrderDto;
import luisa.almeida.restaurant_api_lca.model.Menu;
import luisa.almeida.restaurant_api_lca.model.Order;

@Component
public class Converter {

	public Menu convertToModel(MenuDto dto, Menu model) {

		model.setDishName(dto.getDishName());
		model.setIsAvailable(dto.getIsAvailable());
		model.setIsOnSale(dto.getIsOnSale());
		model.setStartSellingPeriod(dto.getStartSellingPeriod());
		model.setEndSellingPeriod(dto.getEndSellingPeriod());
		return model;
	}

	public MenuDto convertToDto(Menu model, MenuDto dto) {

		dto.setId(model.getId());
		dto.setDishName(model.getDishName());
		dto.setIsAvailable(model.getIsAvailable());
		dto.setIsOnSale(model.getIsOnSale());
		dto.setStartSellingPeriod(model.getStartSellingPeriod());
		dto.setEndSellingPeriod(model.getEndSellingPeriod());
		return dto;
	}
	
	public Order convertToModel(OrderDto dto) {
		Order model = new Order();
		model.setCustomerName(dto.getCustomerName());
		model.setDate(dto.getDate());
		model.setDeliveryAddress(dto.getDeliveryAddress());
		model.setDishName(dto.getDishName());
		model.setQuantity(dto.getQuantity());
		model.setPurchaseStatus(dto.getPurchaseStatus());
		model.setTransactionID(dto.getTransactionID());
		return model;
	}
	
	public OrderDto convertToDto(Order model) {
		OrderDto dto = new OrderDto();
		dto.setCustomerName(model.getCustomerName());
		dto.setDate(model.getDate());
		dto.setDeliveryAddress(model.getDeliveryAddress());
		dto.setDishName(model.getDishName());
		dto.setQuantity(model.getQuantity());
		dto.setPurchaseStatus(model.getPurchaseStatus());
		dto.setTransactionID(model.getTransactionID());
		return dto;
	}

}
