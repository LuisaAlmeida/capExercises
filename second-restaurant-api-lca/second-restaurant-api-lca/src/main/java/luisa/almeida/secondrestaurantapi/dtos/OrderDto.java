package luisa.almeida.secondrestaurantapi.dtos;

import luisa.almeida.secondrestaurantapi.models.PurchaseStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class OrderDto {


    private String uuid;

    @NotBlank(message = "(string) must have a customerName")
    private String customerName;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private PurchaseStatus purchaseStatus;

    @NotBlank(message = "(string) must have a dishName")
    private String dishName;

    @NotBlank(message = "(string) must have a deliveryAddress")
    private String deliveryAddress;

    @NotBlank(message ="(yyyy-mm-dd) must have orderDate")
    private LocalDate orderDate;

    @NotBlank(message = "(integer) must have a quantity")
    private Integer quantity;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
