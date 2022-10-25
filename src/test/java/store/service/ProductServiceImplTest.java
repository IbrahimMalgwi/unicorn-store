package store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.dto.AddProductRequest;
import store.data.dto.AddProductResponse;
import store.data.models.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {
    private ProductService productService;

    private AddProductRequest addProductRequest;

    @BeforeEach
    void setUp() {
        addProductRequest = new AddProductRequest();
        addProductRequest.setName("Coke");
        addProductRequest.setPrice(150);
        addProductRequest.setCategory("BEVERAGES");
    }

    @Test
    void addProductTest() {
        AddProductResponse response = productService.addProduct(addProductRequest);
        assertNotNull(response);
        assertNotNull(response.getMessage());
        assertEquals(1, response.getProductId());
    }

    @Test
    void getProductByIdTest(){
        AddProductResponse response = productService.addProduct(addProductRequest);
        Product foundProduct = productService.getProductById(response.getProductId());
        assertNotNull(foundProduct);
    }
}