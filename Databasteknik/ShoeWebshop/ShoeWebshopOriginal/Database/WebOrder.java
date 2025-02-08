package ShoeWebshop.ShoeWebshopOriginal.Database;

public class WebOrder {

    private Integer id;
    private int customerId;
    private String orderDate;
    private double totalPrice;
    private boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(String status) {
        isActive = status.equals("ACTIVE");
    }

    @Override
    public String toString() {
        return "WebOrder{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderDate='" + orderDate + '\'' +
                ", totalPrice=" + totalPrice +
                ", isActive=" + isActive +
                '}';
    }
}
