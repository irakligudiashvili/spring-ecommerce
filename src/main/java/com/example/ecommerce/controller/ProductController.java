package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return service.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return service.createProduct(product);
    }

    @PostMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product){
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteProduct(id);
    }
}
