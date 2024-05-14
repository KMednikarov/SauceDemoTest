package org.example;

import org.example.utils.SauceDemoWebsite;

public class Main {
    public static void main(String[] args) {
        SauceDemoWebsite sauceDemoWebsite = new SauceDemoWebsite();
        sauceDemoWebsite.login("standard_user", "secret_sauce");
        String url = sauceDemoWebsite.getCurrentUrl();
        System.out.println(url);
        sauceDemoWebsite.addItemsToCart(2);
    }
}