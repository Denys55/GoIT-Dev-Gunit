package com.misterproduct.model.impl;

import com.misterproduct.model.BaseModel;

import java.math.BigDecimal;
import java.util.Objects;


/**
 * Model product
 *
 */
public class Product implements BaseModel {
    private String name;
    private BigDecimal price;
    private boolean sale;
    private int saleCount;
    private BigDecimal salePrice;

    public Product(String name, BigDecimal price, boolean sale) {
        this.name = name;
        this.price = price;
        this.sale = sale;
    }

    public Product(String name, BigDecimal price, boolean sale, int saleCount, BigDecimal salePrice) {
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.saleCount = saleCount;
        this.salePrice = salePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return sale == product.sale && saleCount == product.saleCount && salePrice == product.salePrice && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, sale, saleCount, salePrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", sale=" + sale +
                '}';
    }
}
