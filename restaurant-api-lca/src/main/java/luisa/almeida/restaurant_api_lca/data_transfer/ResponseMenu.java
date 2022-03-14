package luisa.almeida.restaurant_api_lca.data_transfer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import luisa.almeida.restaurant_api_lca.dto.MenuDto;

public class ResponseMenu extends Response{
	
	private List<MenuDto> resValues = new ArrayList<>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	
	public ResponseMenu(String status, String statusCode, String msg) {
		super();
		setMsg(msg);
		setStatus(status);
		LocalDateTime dateTime = LocalDateTime.now();
		String formatedTime = dateTime.format(formatter);
		setSentOn(formatedTime);
		setStatusCode(statusCode);
		setTransactionID(UUID.randomUUID().toString());
	}
	
	public List<MenuDto> getResValues() {
		return resValues;
	}

	public void setResValues(List<MenuDto> resValues) {
		this.resValues = resValues;
	}

	
}
