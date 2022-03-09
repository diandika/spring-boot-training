package com.example.demo.dto;

public class ProductDto {
    private String productName;
    private Long price;
    private Long stock;

    public Long getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public Long getStock() {
        return stock;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStock(Long stock) {  
        this.stock = stock;
    }
}

