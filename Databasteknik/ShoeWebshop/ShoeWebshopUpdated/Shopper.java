package ShoeWebshop.ShoeWebshopUpdated;

import ShoeWebshop.ShoeWebshopUpdated.Database.Customer;
import ShoeWebshop.ShoeWebshopUpdated.Database.OrderTable;
import ShoeWebshop.ShoeWebshopUpdated.Database.Shoe;
import ShoeWebshop.ShoeWebshopUpdated.Database.WebOrder;

import java.util.ArrayList;
import java.util.List;

public class Shopper {

    private Customer customer;
    private WebOrder activeOrder;
    private List<Shoe> shoppingCart;

    Shopper(){
        customer = new Customer();
        activeOrder = new WebOrder();
        shoppingCart = new ArrayList<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public WebOrder getActiveOrder() {
        return activeOrder;
    }

    public List<Shoe> getShoppingCart() {
        return shoppingCart;
    }

    public void addToShoppingCart(Shoe shoe){
        shoppingCart.add(shoe);
    }

    public void removeFromShoppingCart(OrderTable orderTable){
        shoppingCart.remove(orderTable);
    }

    public void clearShoppingCart(){
        shoppingCart.clear();
    }
}
