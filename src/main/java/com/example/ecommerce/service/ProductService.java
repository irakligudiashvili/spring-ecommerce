package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return repository.findById(id);
    }

    public Product createProduct(Product product){
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct){
        return repository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    return repository.save(product);
                }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }
}
