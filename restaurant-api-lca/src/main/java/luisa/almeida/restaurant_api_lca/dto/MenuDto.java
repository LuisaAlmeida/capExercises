package luisa.almeida.restaurant_api_lca.dto;

import java.sql.Date;
import javax.validation.constraints.NotBlank;

public class MenuDto {
	
	private Integer id;
	
	@NotBlank
	private String dishName;
	
	@NotBlank
	private Boolean isAvailable;
	
	@NotBlank
	private Boolean isOnSale;
	
	@NotBlank
	private Date startSellingPeriod;
	
	@NotBlank
	private Date endSellingPeriod;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Boolean getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(Boolean isOnSale) {
		this.isOnSale = isOnSale;
	}

	public Date getStartSellingPeriod() {
		return startSellingPeriod;
	}

	public void setStartSellingPeriod(Date startSellingPeriod) {
		this.startSellingPeriod = startSellingPeriod;
	}
	
	public Date getEndSellingPeriod() {
		return endSellingPeriod;
	}
	
	public void setEndSellingPeriod(Date endSellingPeriod) {
		this.endSellingPeriod = endSellingPeriod;
	}
	
}
