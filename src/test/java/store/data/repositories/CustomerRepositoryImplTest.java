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
    private static final CustomerRepository CUSTOMER_REPOSITORY = new CustomerRepositoryImpl();

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
        CUSTOMER_REPOSITORY.deleteAll();
    }


    @Test
    void saveTest() {
        //before save

        assertEquals(0, customer.getId());
        //saved customer
        Customer savedCustomer = CUSTOMER_REPOSITORY.save(customer);
        //customer has id
        assertEquals(1, savedCustomer.getId());
        //there is one customer un db
        List<Customer> buyersList = CUSTOMER_REPOSITORY.findAll();
        assertEquals(1, buyersList.size());

        //save second customer
        Customer savedSecondCustomer = CUSTOMER_REPOSITORY.save(secondCustomer);
        //second customer's id is 2
        assertEquals(2, savedSecondCustomer.getId());
        //there are twi buyers in do
        buyersList = CUSTOMER_REPOSITORY.findAll();
        assertEquals(2, buyersList.size());
    }

    @Test
    void findByIdTest() {
        Customer firstSavedCustomer = CUSTOMER_REPOSITORY.save(customer);
        Customer secondSavedCustomer = CUSTOMER_REPOSITORY.save(secondCustomer);

        Customer foundCustomer = CUSTOMER_REPOSITORY.findById(secondSavedCustomer.getId());
        assertEquals(foundCustomer, secondSavedCustomer);

    }

    @Test
    void findAllTest() {
        CUSTOMER_REPOSITORY.save(customer);
        var listOfALlBuyersInDb = CUSTOMER_REPOSITORY.findAll();
        assertEquals(1, listOfALlBuyersInDb.size());
    }

    @Test
    void deleteTest() {
     Customer customer1 = CUSTOMER_REPOSITORY.save(customer);
     Customer customer2 = CUSTOMER_REPOSITORY.save(customer);
     var listOfAllBuyersInDb = CUSTOMER_REPOSITORY.findAll();
     assertEquals(2, listOfAllBuyersInDb.size());
//     var deleteBuyer = CUSTOMER_REPOSITORY.delete(customer2);
     assertEquals(1, listOfAllBuyersInDb.size());
    }

    @Test
    void deleteAllTest(){
        CUSTOMER_REPOSITORY.save(customer);
        assertEquals(1, CUSTOMER_REPOSITORY.findAll().size());
        CUSTOMER_REPOSITORY.deleteAll();
        assertEquals(0, CUSTOMER_REPOSITORY.findAll().size());
    }
}