package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<InventoryItem> cartItems;

    public Cart(){
        cartItems = new ArrayList<>();
    }
    public List<InventoryItem> getCartItems() {
        return cartItems;
    }

    public Cart setCartItems(List<InventoryItem> cartItems) {
        this.cartItems = cartItems;
        return this;
    }
}
