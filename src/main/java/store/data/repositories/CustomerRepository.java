package store.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import store.data.models.Customer;

import java.util.List;

public interface CustomerRepository  extends JpaRepository <Customer, Integer> {


    Customer findByEmail(String email);
}
