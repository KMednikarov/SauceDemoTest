package org.example.context;

import org.example.models.Cart;
import org.example.models.Product;

public class TestContext {
    private final Cart cart = new Cart();
    private boolean loggedIn = false;

    public void addProductToCart(Product product){
        cart.getCartProducts().add(product);
    }

    public void removeProductFromCart(Product product){
        cart.getCartProducts().remove(product);
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
