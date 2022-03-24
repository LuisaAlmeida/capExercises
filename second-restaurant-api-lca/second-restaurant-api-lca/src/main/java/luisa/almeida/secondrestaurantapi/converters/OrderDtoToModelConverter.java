package luisa.almeida.secondrestaurantapi.converters;

import luisa.almeida.secondrestaurantapi.dtos.OrderDto;
import luisa.almeida.secondrestaurantapi.models.Order;
import luisa.almeida.secondrestaurantapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoToModelConverter {

    @Autowired
    OrderService service;

    public Order convertToModel(OrderDto dto) {
        Order model = (dto.getUuid() != null ?
        service.findByUuid(dto.getUuid()).get() : new Order());
        model.setUuid(dto.getUuid());
        model.setDishName(dto.getDishName());
        model.setDeliveryAddress(dto.getDeliveryAddress());
        model.setOrderDate(dto.getOrderDate());
        model.setCustomerName(dto.getCustomerName());
        model.setPurchaseStatus(dto.getPurchaseStatus());
        model.setQuantity(dto.getQuantity());
        return model;
    }
}
