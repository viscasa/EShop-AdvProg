package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api. BeforeEach;
import org.junit.jupiter.api. Test;
import org.junit.jupiter.api.extension. ExtendWith;
import org.mockito. InjectMocks;
import org.mockito.junit. jupiter. MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org. junit.jupiter.api.Assertions .*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreateandFind() {
        Product product = new Product();
        product.setProductName ("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository. findAll() ;
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository. findAll() ;
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductName ("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository. create(product1);

        Product product2 = new Product();
        product2.setProductName ("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Product product3 = productRepository.findById(product1.getProductId()) ;
        assertEquals(product1.getProductId(), product3.getProductId());
        Product product4 = productRepository.findById(product2.getProductId()) ;
        assertEquals(product2.getProductId(), product4.getProductId());
        assertNull(productRepository.findById("asjkfsa"));

        Iterator<Product> productIterator = productRepository. findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    // Positive Test for Edit Product
    @Test
    void testUpdateProductSuccess() {
        Product existingProduct = new Product();
        existingProduct.setProductName("Original Product");
        existingProduct.setProductQuantity(50);
        productRepository.create(existingProduct);

        Product existingProduct2 = new Product();
        existingProduct2.setProductName("Original Product2");
        existingProduct2.setProductQuantity(70);
        productRepository.create(existingProduct2);

        Product updatedProduct = new Product();
        updatedProduct.setProductId(existingProduct.getProductId());
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(100);

        Product updatedProduct2 = new Product();
        updatedProduct2.setProductId(existingProduct2.getProductId());
        updatedProduct2.setProductName("Updated Product2");
        updatedProduct2.setProductQuantity(100);

        Product result = productRepository.update(updatedProduct);
        Product result2 = productRepository.update(updatedProduct2);

        assertNotNull(result);
        assertNotNull(result2);
        assertEquals("Updated Product", result.getProductName());
        assertEquals("Updated Product2", result2.getProductName());
        assertEquals(100, result.getProductQuantity());
        assertEquals(100, result2.getProductQuantity());
    }

    // Negative Test for Edit Product
    @Test
    void testUpdateProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("99999");
        updatedProduct.setProductName("Nonexistent Product");
        updatedProduct.setProductQuantity(100);

        Product result = productRepository.update(updatedProduct);

        assertNull(result);
    }

    // Positive Test for Delete Product
    @Test
    void testDeleteProductSuccess() {
        Product existingProduct = new Product();
        existingProduct.setProductName("Test Product");
        existingProduct.setProductQuantity(20);
        productRepository.create(existingProduct);

        boolean result = productRepository.deleteById(existingProduct.getProductId());

        assertTrue(result);
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    // Negative Test for Delete Product
    @Test
    void testDeleteProductNotFound() {
        boolean result = productRepository.deleteById("99999");

        assertFalse(result);
    }
}
