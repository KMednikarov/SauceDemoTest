package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartProducts;

    public Cart(){
        cartProducts = new ArrayList<>();
    }
    public List<Product> getCartProducts() {
        return cartProducts;
    }

    public Cart setCartProducts(List<Product> cartProducts) {
        this.cartProducts = cartProducts;
        return this;
    }
}
