package store.data.repositories;

import lombok.Data;
import store.data.models.Product;

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
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void delete(Product product) {

    }
}
