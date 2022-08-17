package com.misterproduct.repository.impl;

import com.misterproduct.model.impl.Product;
import com.misterproduct.repository.BaseRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements BaseRepository<Product> {

    private final List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        Product productA = new Product("A", BigDecimal.valueOf(1.25), true, 3, BigDecimal.valueOf(3.0));
        Product productB = new Product("B", BigDecimal.valueOf(4.25), false);
        Product productC = new Product("C", BigDecimal.valueOf(1.00), true, 6, BigDecimal.valueOf(5.0));
        Product productD = new Product("D", BigDecimal.valueOf(0.75), false);

        products.add(productA);
        products.add(productB);
        products.add(productC);
        products.add(productD);
    }

    @Override
    public Optional<Product> getByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findAny();
    }
}
