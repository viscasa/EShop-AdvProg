package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.List;


public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    Product findById(String id);
    Product update(Product product);
    void deleteById(String id);
}
