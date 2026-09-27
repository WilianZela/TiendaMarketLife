package com.mishistudios.tiendaweb.dto;

import com.mishistudios.tiendaweb.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String sellerUsername;
    private LocalDateTime createdAt;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.sellerUsername = product.getSeller().getUsername();
        this.createdAt = product.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
