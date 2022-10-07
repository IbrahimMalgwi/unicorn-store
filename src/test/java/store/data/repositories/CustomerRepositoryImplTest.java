package store.data.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.models.Customer;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryImplTest {
    private Customer customer;
    private Customer secondCustomer;
    private static final CustomerRepository customerRepository
            = new CustomerRepositoryImpl();

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setFirstName("Judit");
        customer.setLastName("Ibrahim");
        customer.setEmail("JudithIbrahim@gmail.com");
        customer.setPassword("DaddyIsTheBest2022");

        secondCustomer = new Customer();
        secondCustomer.setFirstName("Joanna");
        secondCustomer.setLastName("Ibrahim");
        secondCustomer.setEmail("JoannaIbrahim@gmail.com");
        secondCustomer.setPassword("DaddyIsTheBest2022");

    }

    @AfterEach
    void tearDown(){
        customerRepository.deleteAll();
    }


    @Test
    void saveTest() {
        assertEquals(0, customer.getId());
        Customer savedCustomer = customerRepository.save(customer);
        assertEquals(1, savedCustomer.getId());
        List<Customer> buyersList = customerRepository.findAll();
        assertEquals(1, buyersList.size());

        Customer savedSecondCustomer = customerRepository.save(secondCustomer);
        assertEquals(2, savedSecondCustomer.getId());
        buyersList = customerRepository.findAll();
        assertEquals(2, buyersList.size());
    }

    @Test
    void findByIdTest() {
        Customer firstSavedCustomer = customerRepository.save(customer);
        Customer secondSavedCustomer =customerRepository.save(secondCustomer);

        Customer foundCustomer = customerRepository.findById(secondSavedCustomer.getId());
        assertEquals(foundCustomer, secondSavedCustomer);
    }

    @Test
    void findAllTest() {
        customerRepository.save(customer);
        var listOfALlBuyersInDb = customerRepository.findAll();
        assertEquals(1, listOfALlBuyersInDb.size());
    }

    @Test
    void deleteTest() {
     Customer savedCustomer = customerRepository.save(customer);
     customerRepository.delete(savedCustomer);
     assertFalse(customerRepository.findAll().contains(savedCustomer));
    }

    @Test
    void deleteAllTest(){
        customerRepository.save(customer);
        assertEquals(1, customerRepository.findAll().size());
        customerRepository.deleteAll();
        assertEquals(0, customerRepository.findAll().size());
    }
}