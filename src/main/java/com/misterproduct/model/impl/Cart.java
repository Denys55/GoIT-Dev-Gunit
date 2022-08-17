package com.misterproduct.model.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shopping cart
 */
public class Cart {
    private final Map<Product, Integer> products;
    private static final int ONE_PRODUCT = 1;
    private static final int ZERO_PRICE = 0;

    public Cart() {
        products = new LinkedHashMap<>();
    }

    /**
     * Add product to shopping cart
     *
     * @param product product client add to cart
     */
    public void addProductToCart(Product product) {
        if (products.containsKey(product)) {
            Integer countProducts = products.get(product);
            countProducts = countProducts + ONE_PRODUCT;
            products.put(product, countProducts);
            return;
        }
        products.put(product, ONE_PRODUCT);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal result = new BigDecimal(ZERO_PRICE);
        if (products.size() == 0) {
            return result;
        }

        Map<Product, BigDecimal> productPrice = calculateCostEveryProduct(products);
        for (Map.Entry<Product, BigDecimal> product : productPrice.entrySet()) {
            result = result.add(product.getValue());
        }

        return result;
    }

    /**
     * Calculate total cost ever products
     *
     * @param products shopping cart
     * @return Product - product,  BigDecimal - total cost every product depends on count
     */
    private Map<Product, BigDecimal> calculateCostEveryProduct(Map<Product, Integer> products) {
        Map<Product, BigDecimal> result = new HashMap<>();

        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            BigDecimal priceOneTypeProduct = calculatePriceProduct(product.getKey(), product.getValue());
            result.put(product.getKey(), priceOneTypeProduct);
        }

        return result;
    }

    /**
     * Calculate price one type product
     *
     * @param product that client adds to cart
     * @param count of products
     * @return total price one type product
     */
    private BigDecimal calculatePriceProduct(Product product, Integer count) {
        if (product.isSale()) {
            int productWithoutSale = count % product.getSaleCount();
            int productWithSale = count - productWithoutSale;

            BigDecimal totalCostProductWithoutSale = product.getPrice().multiply(BigDecimal.valueOf(productWithoutSale));

            BigDecimal totalCostProductWithSale = product.getSalePrice().multiply(BigDecimal.valueOf((productWithSale/product.getSaleCount())));

            return totalCostProductWithoutSale.add(totalCostProductWithSale);
        }

        return product.getPrice().multiply(BigDecimal.valueOf(count));
    }
    public void clearCart() {
        products.clear();
    }
}
