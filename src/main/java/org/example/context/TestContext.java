package org.example.context;

import org.example.models.Cart;
import org.example.models.InventoryItem;

public class TestContext {
    private Cart cart = new Cart();
    private boolean loggedIn = false;

    public void addItemToCart(InventoryItem item){
        cart.getCartItems().add(item);
    }

    public void removeItemFromCart(InventoryItem item){
        cart.getCartItems().remove(item);
    }

    public Cart getCart() {
        return cart;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public TestContext setLoggedIn(boolean logged) {
        loggedIn = logged;
        return this;
    }
}
