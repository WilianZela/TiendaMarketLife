package com.mishistudios.tiendaweb.controller;

import com.mishistudios.tiendaweb.dto.ProductRequest;
import com.mishistudios.tiendaweb.dto.ProductResponse;
import com.mishistudios.tiendaweb.model.Product;
import com.mishistudios.tiendaweb.model.User;
import com.mishistudios.tiendaweb.repository.ProductRepository;
import com.mishistudios.tiendaweb.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<ProductResponse> list() {
        return productRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductRequest request, Authentication authentication) {
        User seller = userRepository.findByUsername(authentication.getName()).orElseThrow();
        Product product = new Product(request.getName(), request.getDescription(), request.getPrice(), seller);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse(product));
    }
}
