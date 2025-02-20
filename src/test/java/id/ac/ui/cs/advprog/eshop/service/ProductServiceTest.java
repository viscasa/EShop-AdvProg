package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ProductServiceImpl();
        service.initRepository();
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(12);

        Product createdProduct = service.create(product);
        assertNotNull(createdProduct);
        assertEquals(product.getProductName(), createdProduct.getProductName());
    }

    @Test
    void testUpdateProductSuccess() {
        Product existingProduct = new Product();
        existingProduct.setProductName("Original Product");
        existingProduct.setProductQuantity(50);
        service.create(existingProduct);

        Product existingProduct2 = new Product();
        existingProduct2.setProductName("Original Product2");
        existingProduct2.setProductQuantity(55);
        service.create(existingProduct2);

        Product updatedProduct = new Product();
        updatedProduct.setProductId(existingProduct.getProductId());
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(100);

        Product updatedProduct2 = new Product();
        updatedProduct2.setProductId(existingProduct2.getProductId());
        updatedProduct2.setProductName("Updated Product");
        updatedProduct2.setProductQuantity(100);

        Product updatedProduct3 = new Product();
        updatedProduct3.setProductId("1213");
        updatedProduct3.setProductName("Updated Product");
        updatedProduct3.setProductQuantity(100);

        Product result = service.update(updatedProduct);
        Product result2 = service.update(updatedProduct2);
        Product result3 = service.update(updatedProduct3);

        assertNotNull(result);
        assertNotNull(result2);
        assertNotNull(result3);
        assertEquals("Updated Product", result.getProductName());
        assertEquals(100, result.getProductQuantity());
    }

    @Test
    void testFindAll() {
        Product existingProduct = new Product();
        existingProduct.setProductName("Original Product");
        existingProduct.setProductQuantity(50);
        service.create(existingProduct);

        List<Product> result = service.findAll();
        assertNotNull(result);

        Product getProduct = service.findById(existingProduct.getProductId());
        assertNotNull(getProduct);
        assertEquals(existingProduct.getProductName(), getProduct.getProductName());

        assertNull(service.findById("dfjds"));
    }

    @Test
    void testDeleteProductSuccess() {
        Product existingProduct = new Product();
        existingProduct.setProductName("Original Product");
        existingProduct.setProductQuantity(50);
        service.create(existingProduct);

        service.deleteById(existingProduct.getProductId());
        assertNull(service.findById(existingProduct.getProductId()));
    }
}
