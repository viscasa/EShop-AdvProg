package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findById(String id) {
        for (Product product : findAll()) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null; // Or throw an exception
    }

    @Override
    public Product update(Product product) {
        List<Product> products = findAll();

        for (int i = 0; i < products.size(); i++) {
            Product compProd = products.get(i);
            if (compProd.getProductId().equals(product.getProductId())) {
                compProd.setProductName(product.getProductName());
                compProd.setProductQuantity(product.getProductQuantity());
                break;
            }
        }
        return product;
    }
}
