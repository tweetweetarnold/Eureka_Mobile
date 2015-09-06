package arnold.eureka_mobile.Entity;

import java.util.ArrayList;

/**
 * Created by Arnold on 9/6/2015.
 */
public class Cart {
    ArrayList<Food> cart;

    public Cart(){
        this.cart = new ArrayList<>();
    }

    public void addToCart(Food food){
        cart.add(food);
    }

    public ArrayList<Food> getCart(){
        return cart;
    }
}
