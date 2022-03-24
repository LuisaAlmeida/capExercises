package luisa.almeida.restaurant_api_lca.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import luisa.almeida.restaurant_api_lca.data_transfer.Converter;
import luisa.almeida.restaurant_api_lca.data_transfer.ResponseOrder;
import luisa.almeida.restaurant_api_lca.dto.OrderDto;
import luisa.almeida.restaurant_api_lca.model.Order;
import luisa.almeida.restaurant_api_lca.model.PurchaseStatus;
import luisa.almeida.restaurant_api_lca.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	private OrderService service;

	@Autowired
	private Converter converter;

	//request body vai buscar pelo transaction id
	@PostMapping("/getOrder")
	public ResponseOrder getOrder() {
		ResponseOrder responseOK = new ResponseOrder("OK", "200", "Order get Success");
		List<Order> orderList = service.getOrder();
		if(orderList.isEmpty()) {
			ResponseOrder responseOrder = new ResponseOrder("NOK", "400", "No orders found");

			return responseOrder;
		}
		List<OrderDto> orderDtoList = new ArrayList<>();
		for(Order order : orderList) {
			orderDtoList.add(converter.convertToDto(order));
		}
			responseOK.setResValues(orderDtoList);
		return responseOK;
	}

	@PostMapping
	@RequestMapping("/addOrder")
	//TODO gerar id, adicionar customer name, purchase status, dish name, classe id de java, transaction id = classe id java, id get to string
	public ResponseOrder addOrder(@RequestBody OrderDto dto) {
		try {
			if(dto.getCustomerName().isBlank() || dto.getDeliveryAddress().isBlank() || dto.getDishName().isBlank()
					|| dto.getDate() == null || dto.getQuantity() == null) {
				ResponseOrder badResponse = new ResponseOrder("NOK", "400", "Bad request");
				return badResponse;
			}
			Order order = new Order();
			order = converter.convertToModel(dto);
			service.addOrder(order);
			ResponseOrder responseOK = new ResponseOrder(
					"OK", "200", "Order add Success");
			return responseOK;
		} catch (Exception e) {
			ResponseOrder badResponse = new ResponseOrder(
					"NOK", "500", "Error: Fetch the Error");
			badResponse.setMsg("OK");
			return badResponse;
		}
	}
	
	//TODO garantir que o order existe
	@PostMapping
	@RequestMapping("/updateOrder")
	public ResponseOrder updateOrder(@RequestBody OrderDto dto) {
		try {
			Order order = service.findById(dto.getId());
			order = converter.convertToModel(dto);
			service.addOrder(order);
			ResponseOrder responseOk = new ResponseOrder(
					"OK", "200", "Order update Success");
			return responseOk;
		} catch (Exception e) {
			ResponseOrder badResponse = new ResponseOrder(
					"NOK", "500", "Error: Fetch the Error");
			return badResponse;
		}
	}

	//	@PostMapping
	//	@RequestMapping("/changePurchaseStatus")
	//	public ResponseOrder changePurchaseStatus(@RequestBody OrderDto dto) {
	//		try {
	//			Order order = service.findById(dto.getId());
	//			order.setPurchaseStatus(dto.getPurchaseStatus());
	//			service.save(order);
	//			ResponseOrder responseOk = new ResponseOrder(
	//					"OK", "200", "PurchaseStatus update Success");
	//			return responseOk;
	//		} catch (Exception e) {
	//			ResponseOrder badResponse = new ResponseOrder(
	//					"NOK", "500", "Error: Fetch the Error");
	//			return badResponse;
	//		}
	//	}

	@PostMapping("/getPurchaseStatus")
	public PurchaseStatus getPurchaseStatus(@RequestBody OrderDto dto) {
		return service.getPurchaseStatus(dto.getId());
	}
	
	//TODO sempre validar se pedido existe ou não
	@PostMapping("/changePurchaseStatus")
	public Order changePurchaseStatus(@RequestBody OrderDto dto) throws ParseException {

		PurchaseStatus purchaseStatus = service.getPurchaseStatus(dto.getId());
		Order order = service.findById(dto.getId());
		order = converter.convertToModel(dto);

		ResponseOrder okResponse = new ResponseOrder("OK", "200", "PurchaseStatus update Success");
		String orderHour = okResponse.getSentOn();
		Date orderDate = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss").parse(orderHour);

		switch (purchaseStatus) {
		case WAINTING_CONFIRMATION:
			order.setPurchaseStatus(PurchaseStatus.ORDER_RECEIVED);
			order.setOrderDate(orderDate);
			break;
		case ORDER_RECEIVED:
			order.setPurchaseStatus(PurchaseStatus.BEING_PREPARED);
			order.setOrderDate(orderDate);
			break;
		case BEING_PREPARED:
			order.setPurchaseStatus(PurchaseStatus.ON_THE_WAY);
			order.setOrderDate(orderDate);
			break;
		case ON_THE_WAY:
			order.setPurchaseStatus(PurchaseStatus.DELIVERED);
			order.setOrderDate(orderDate);
			break;
		default:
			System.out.println("Order Delivered");
			break;
		}

		return service.save(order);

	}

}
