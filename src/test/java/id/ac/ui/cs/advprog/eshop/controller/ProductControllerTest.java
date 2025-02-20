package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class ProductControllerTest {

    private ProductController productController;
    private ProductService productService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        productController = new ProductController();
        productController.service = productService;
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() {
        Model model = mock(Model.class);
        String result = productController.createProductPage(model);
        assertEquals("CreateProduct", result);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product product = new Product();
        product.setProductName("New Product");

        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        when(productService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/product/list"))
                .andExpect(view().name("ProductList"));

        verify(productService, times(1)).findAll();
    }

    @Test
    void testEditProductPageSuccess() throws Exception {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Existing Product");

        when(productService.findById("123")).thenReturn(product);

        mockMvc.perform(get("/product/edit/123"))
                .andExpect(view().name("EditProduct"));

        verify(productService, times(1)).findById("123");
    }

    @Test
    void testEditProductPost() throws Exception {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Updated Product");

        mockMvc.perform(post("/product/edit/123")
                        .flashAttr("product", product))
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).update(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/product/delete/123"))
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).deleteById("123");
    }
}
