package store.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import store.data.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
Optional<Product> findByName(String name);
}
