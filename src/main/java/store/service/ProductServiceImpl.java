package store.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import store.data.dto.AddProductRequest;
import store.data.dto.AddProductResponse;
import store.data.models.Category;
import store.data.models.Product;
import store.data.repositories.ProductRepository;

import java.math.BigDecimal;

@AllArgsConstructor
@Component
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;


    @Override
    public AddProductResponse addProduct(AddProductRequest addProductRequest) {
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(addProductRequest.getPrice()));
        product.setCategory(Category.valueOf(addProductRequest.getCategory().toUpperCase()));
        product.setName(addProductRequest.getName());
        Product savedProduct = productRepository.save(product);

        AddProductResponse response = new AddProductResponse();
        response.setProductId(savedProduct.getId());
        response.setMessage("Product created successfully");
        response.setStatusCode(201);
        return response;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    public Product save(){
        return productRepository.save();
    }

}
