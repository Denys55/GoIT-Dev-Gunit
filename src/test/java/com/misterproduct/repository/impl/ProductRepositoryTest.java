package com.misterproduct.repository.impl;

import com.misterproduct.model.impl.Product;
import com.misterproduct.repository.BaseRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductRepositoryTest {
    BaseRepository repository;
    Product productB;

    @Before
    public void setUp() throws Exception {
        repository = new ProductRepository();
        productB = new Product("B", new BigDecimal(4.25), false);
    }

    @Test
    public void getProductByNameShouldReturnProductB() {
        //given
        String name = productB.getName();

        //when
        Product productByName = (Product) repository.getByName(name).get();

        //then
        assertEquals(productB, productByName);
    }

    @Test()
    public void getProductByNameShouldReturnNotPresent() {
        //given
        String name = "AA";

        //when
        boolean actual = repository.getByName(name).isPresent();

        assertFalse(actual);
    }
}