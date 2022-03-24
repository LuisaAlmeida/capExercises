package luisa.almeida.restaurant_api_lca.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class MenuDto {
	
	private Integer id;
	
	@NotBlank
	private String dishName;
	
	@NotBlank
	private Boolean isAvailable;
	
	@NotBlank
	private LocalDate startDate;
	
	@NotBlank
	private LocalDate endDate;

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startSellingPeriod) {
		this.startDate = startSellingPeriod;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endSellingPeriod) {
		this.endDate = endSellingPeriod;
	}
	
}
