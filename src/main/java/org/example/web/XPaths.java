package org.example.web;

import org.openqa.selenium.By;

public class XPaths {
    public static final String ITEM_NAME = ".//div[contains(@data-test,'inventory-item-name')]";
    public static final String ITEM_DESCRIPTION = ".//div[contains(@data-test,'inventory-item-desc')]";
    public static final String ITEM_PRICE = ".//div[contains(@data-test,'inventory-item-price')]";
    public static final String ADD_TO_CART_BTN = ".//button[contains(@data-test,'add-to-cart-sauce')]";
    public static final String REMOVE_FROM_CART_BTN = ".//button[contains(@data-test,'remove-sauce')]";
}
