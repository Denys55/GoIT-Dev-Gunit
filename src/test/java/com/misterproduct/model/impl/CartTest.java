package com.misterproduct.model.impl;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class CartTest {
    Cart cart;
    Product productA;
    Product productB;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
        productA = new Product("A", new BigDecimal(5), true, 5, new BigDecimal(4));
        productB = new Product("B", new BigDecimal(2), false);
    }

    @Test
    public void shouldAddProductToCart() throws NoSuchFieldException, IllegalAccessException {
        //when
        cart.addProductToCart(productA);
        cart.addProductToCart(productA);
        cart.addProductToCart(productB);

        Class<? extends Cart> cartClass = cart.getClass();
        Field products = cartClass.getDeclaredField("products");
        products.setAccessible(true);
        Map<Product, Integer> cartProducts = (Map<Product, Integer>) products.get(cart);

        //then
        int expected = 2;
        int actual = cartProducts.size();
        assertEquals(expected, actual);
    }

    @Test
    public void getTotalPriceShouldReturnThirtyEight() {
        //given
        int countProductA = 7;
        for (int i = 0; i < countProductA; i++) {
            cart.addProductToCart(productA);
        }

        int countProductB = 4;
        for (int i = 0; i < countProductB; i++) {
            cart.addProductToCart(productB);
        }

        //when
        BigDecimal actual = cart.getTotalPrice();

        //then
        BigDecimal expected = new BigDecimal(22);
        assertEquals(expected, actual);
    }
}