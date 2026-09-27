package com.mishistudios.tiendaweb.repository;

import com.mishistudios.tiendaweb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByCreatedAtDesc();
}
