package luisa.almeida.restaurant_api_lca.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu_lca")
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 255)
	private String dishName;
	
	@Column
	private Boolean isAvailable;
	
	@Column
	private Boolean isOnSale;
	
	@Column(nullable = false)
	private Date startSellingPeriod;
	
	@Column(nullable = false)
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
