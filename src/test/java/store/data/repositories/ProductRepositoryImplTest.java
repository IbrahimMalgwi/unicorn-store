package store.data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.models.Category;
import store.data.models.Product;
import store.exception.ProductNotFoundException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryImplTest {
    private ProductRepository productRepository = new ProductRepositoryImpl();

    private Product product;

    @BeforeEach
    void setUp(){
        product = new Product();
        product.setName("milk");
        product.setCategory(Category.BEVERAGES);
        product.setPrice(BigDecimal.valueOf(50));

    }

    @Test
    void saveProductTest(){
        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct);
    }

    @Test
    void findProductById(){
        Product savedProduct = productRepository.save(product);
        Product foundProduct = productRepository.findById(savedProduct.getId());
        assertNotNull(foundProduct);
        assertEquals(savedProduct.getId(), foundProduct.getId());
    }

    @Test
    void testThatExceptionIsThrownWhenInvalidIdPassedOfFindById(){
        productRepository.save(product);
        assertThrows(ProductNotFoundException.class, ()-> productRepository.findById(900));
    }

}