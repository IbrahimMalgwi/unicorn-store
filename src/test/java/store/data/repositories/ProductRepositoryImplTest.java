package store.data.repositories;

import org.junit.jupiter.api.Test;
import store.data.models.Category;
import store.data.models.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryImplTest {
    private ProductRepository productRepository = new ProductRepositoryImpl();

    @Test
    void saveProductTest(){
        Product product = new Product();
        product.setName("milk");
        product.setCategory(Category.BEVERAGES);
        product.setPrice(BigDecimal.valueOf(50));

        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct);
    }

}