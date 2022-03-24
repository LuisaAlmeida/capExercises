package luisa.almeida.secondrestaurantapi.controllers;

import luisa.almeida.secondrestaurantapi.converters.OrderDtoToModelConverter;
import luisa.almeida.secondrestaurantapi.converters.OrderModelToDtoConverter;
import luisa.almeida.secondrestaurantapi.dtos.OrderDto;
import luisa.almeida.secondrestaurantapi.models.Order;
import luisa.almeida.secondrestaurantapi.models.PurchaseStatus;
import luisa.almeida.secondrestaurantapi.responses.OrderResponse;
import luisa.almeida.secondrestaurantapi.services.MenuService;
import luisa.almeida.secondrestaurantapi.services.OrderService;

import luisa.almeida.secondrestaurantapi.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OrderDtoToModelConverter dtoToModel;

    @Autowired
    private OrderModelToDtoConverter modelToDto;


    @PostMapping
    @RequestMapping("/getOrder")
    public OrderResponse getOrder(@RequestBody OrderDto dto) {
        OrderResponse response = new OrderResponse();
        try {
            Optional<Order> orderOptional = service.findByUuid(dto.getUuid());
            if(orderOptional.isPresent()) {
                response.getResValues().add(modelToDto.convertToDto(orderOptional.get()));
                response.generateResponse("OK","200","Here is the Order");
            }
        } catch (Exception e) {
            response.generateResponse("NOK,", "400", "Error: " + e.getMessage());
        }
        return response;
    }

    @PostMapping
    @RequestMapping("/addOrder")
    public OrderResponse addOrder(@RequestBody OrderDto dto) {
        OrderResponse response = new OrderResponse();
        try {
            Order model = dtoToModel.convertToModel(dto);
            Pair<Boolean, String> pair = service.addOrder(model);
            if(pair._first) {
                response.generateResponse("OK", "200", "Order add Successful");
            } else {
                response.generateResponse("NOK", "400", pair._second);
            }
        } catch (Exception e) {
            response.generateResponse("NOK", "400", "ERROR" + e.getMessage());
        }
        return response;
    }


    @PostMapping
    @RequestMapping("/updateOrder")
    public OrderResponse updateOrder(@RequestBody OrderDto dto) {
        OrderResponse response = new OrderResponse();
        Optional<Order> order = service.findByUuid(dto.getUuid());
        try {
            if(dto.getUuid() == null || dto.getQuantity() == null || dto.getPurchaseStatus() == null ||
                    dto.getDishName().isEmpty() || dto.getDeliveryAddress().isEmpty()) {
                response.generateResponse("NOK,", "400", "NOT OKAY");
            }
            Order updateOrder = dtoToModel.convertToModel(dto);
            boolean controlFlag = service.updateOrder(updateOrder);
            if(controlFlag){
                updateOrder.setQuantity(dto.getQuantity());
                updateOrder.setUuid(dto.getUuid());
                updateOrder.setDishName(dto.getDishName());
                updateOrder.setDeliveryAddress(dto.getDeliveryAddress());
                response.getResValues().add(modelToDto.convertToDto(order.get()));
                response.generateResponse("OK","200","Order updated");

                switch (updateOrder.getPurchaseStatus()) {
                    case NEW_ORDER:
                        updateOrder.setPurchaseStatus(PurchaseStatus.BEING_PREPARED);
                        break;
                    case BEING_PREPARED:
                        updateOrder.setPurchaseStatus(PurchaseStatus.ON_THE_WAY);
                        break;
                    case ON_THE_WAY:
                        updateOrder.setPurchaseStatus(PurchaseStatus.DELIVERED);
                        break;
                    case DELIVERED:
                        break;
                    default:
                        updateOrder.setPurchaseStatus(PurchaseStatus.ORDER_CANCELED);
                        break;
                }
            } else {
                response.generateResponse("NOK,", "400", "NOT OKAY");
            }
        } catch (Exception e) {
            response.generateResponse("NOK,", "400", "Error: " + e.getMessage());
        }
        return response;
    }

    @PostMapping("/cancelOrder")
    public OrderResponse calcelOrder(@RequestBody OrderDto dto) {
        OrderResponse response = new OrderResponse();
        try {
            if (!dto.getUuid().isBlank() && service.cancelOrder(dto.getUuid())) {
                response.generateResponse("OK", "200", "Cancel order success");
            } else {
                response.generateResponse("NOK", "400", "Error");
            }
        } catch (Exception e) {
            response.generateResponse("NOK","400","ERROR: " + e.getMessage());
        }
        return response;
    }





}


