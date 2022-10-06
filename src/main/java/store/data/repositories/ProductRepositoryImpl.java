package store.data.repositories;

import lombok.Data;
import store.data.models.Product;
import store.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductRepositoryImpl implements ProductRepository{

    private final List<Product> products = new ArrayList<>();

    @Override
    public Product save(Product product) {
        int newId = generateId();
        product.setId(newId);
        products.add(product);
        return product;
    }

    private int generateId() {
        int listSize  = products.size();
        return listSize+1;
    }

    @Override
    public Product findById(int id) {
        for (Product product : products ){
            if (product.getId() == id) return product;
        }
        throw new ProductNotFoundException("" +
                String.format("Product with id %d doesn't exsit", id));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void delete(Product product) {

    }
}
