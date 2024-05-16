package org.example.models;

import org.example.utils.ValueExtractUtil;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static final double TAX_RATE = 0.08;
    private List<Product> cartProducts;
    private double subTotalPrice;
    private double taxPrice;
    private double totalPrice;

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

    public double getSubTotalPrice() {
        subTotalPrice = 0;
        for (Product cartProduct : cartProducts) {
            subTotalPrice += Double.parseDouble(ValueExtractUtil.fetchPrice(cartProduct.price()));
        }
        return subTotalPrice;
    }

    public double getTaxPrice() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);
        String taxPrice = df.format(getSubTotalPrice() * TAX_RATE);
        return Double.parseDouble(taxPrice);
    }

    public double getTotalPrice() {
        return getSubTotalPrice() + getTaxPrice();
    }
}
