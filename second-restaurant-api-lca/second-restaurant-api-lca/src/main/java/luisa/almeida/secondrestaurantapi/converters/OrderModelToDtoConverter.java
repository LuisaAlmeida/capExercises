package luisa.almeida.secondrestaurantapi.converters;

import luisa.almeida.secondrestaurantapi.dtos.OrderDto;
import luisa.almeida.secondrestaurantapi.models.Order;
import luisa.almeida.secondrestaurantapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderModelToDtoConverter {

    @Autowired
    OrderService service;

    public OrderDto convertToDto(Order model) {
        OrderDto dto = new OrderDto();
        dto.setUuid(model.getUuid());
        dto.setDishName(model.getDishName());
        dto.setOrderDate(model.getOrderDate());
        dto.setCustomerName(model.getCustomerName());
        dto.setDeliveryAddress(model.getDeliveryAddress());
        dto.setQuantity(model.getQuantity());
        dto.setPurchaseStatus(model.getPurchaseStatus());
        return dto;
    }


}
