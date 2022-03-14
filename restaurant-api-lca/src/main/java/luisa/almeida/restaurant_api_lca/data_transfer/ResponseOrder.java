package luisa.almeida.restaurant_api_lca.data_transfer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import luisa.almeida.restaurant_api_lca.dto.OrderDto;

public class ResponseOrder extends Response {
	
	private List<OrderDto> resValues = new ArrayList<>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	
	public ResponseOrder(String status, String statusCode, String msg) {
		super();
		setMsg(msg);
		setStatus(status);
		LocalDateTime dateTime = LocalDateTime.now();
		String formatedTime = dateTime.format(formatter);
		setSentOn(formatedTime);
		setStatusCode(statusCode);
		setTransactionID(UUID.randomUUID().toString());
	}
	
	public List<OrderDto> getResValues() {
		return resValues;
	}
	
	public void setResValues(List<OrderDto> resValues) {
		this.resValues = resValues;
	}
	
	public void addResValues(OrderDto dto) {
		resValues.add(dto);
		}

//	public ResponseOrder sendOkResponse(OrderDto dto, String msg) {
//		ResponseOrder okResponse = new ResponseOrder();
//		UUID uuid = UUID.randomUUID();
//		LocalDateTime dateTime = LocalDateTime.now();
//		String formatedTime = dateTime.format(formatter);
//		setSentOn(formatedTime);
//
//		okResponse.setStatusCode("200");
//		okResponse.setStatus("OK");
//		okResponse.setSentOn(formatedTime);
//		okResponse.setTransactionID(uuid.toString());
//
//		if (dto.getId().equals(null)) {
//		okResponse.setMsg("Order added successfully");
//		okResponse.addResValues(dto);
//		}
//		okResponse.setMsg("Order" + msg + "successfully");
//		return okResponse;
//		}
}
