package store.data.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.models.Buyer;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuyerRepositoryImplTest {
    private Buyer buyer;
    private Buyer secondBuyer;
    private static final BuyerRepository buyerRepository = new BuyerRepositoryImpl();

    @BeforeEach
    void setUp() {
        buyer = new Buyer();
        buyer.setFirstName("Judit");
        buyer.setLastName("Ibrahim");
        buyer.setEmail("JudithIbrahim@gmail.com");
        buyer.setPassword("DaddyIsTheBest2022");

        secondBuyer = new Buyer();
        secondBuyer.setFirstName("Joanna");
        secondBuyer.setLastName("Ibrahim");
        secondBuyer.setEmail("JoannaIbrahim@gmail.com");
        secondBuyer.setPassword("DaddyIsTheBest2022");

    }

    @AfterEach
    void tearDown(){
        buyerRepository.deleteAll();
    }


    @Test
    void saveTest() {
        //before save

        assertEquals(0, buyer.getId());
        //saved buyer
        Buyer savedBuyer = buyerRepository.save(buyer);
        //buyer has id
        assertEquals(1, savedBuyer.getId());
        //there is one buyer un db
        List<Buyer> buyersList = buyerRepository.findAll();
        assertEquals(1, buyersList.size());

        //save second buyer
        Buyer savedSecondBuyer = buyerRepository.save(secondBuyer);
        //second buyer's id is 2
        assertEquals(2, savedSecondBuyer.getId());
        //there are twi buyers in do
        buyersList = buyerRepository.findAll();
        assertEquals(2, buyersList.size());
    }

    @Test
    void findByIdTest() {
        Buyer firstSavedBuyer = buyerRepository.save(buyer);
        Buyer secondSavedBuyer = buyerRepository.save(secondBuyer);

        Buyer foundBuyer = buyerRepository.findById(secondSavedBuyer.getId());
        assertEquals(foundBuyer, secondSavedBuyer);

    }

    @Test
    void findAllTest() {
        buyerRepository.save(buyer);
        var listOfALlBuyersInDb = buyerRepository.findAll();
        assertEquals(1, listOfALlBuyersInDb.size());
    }

    @Test
    void deleteTest() {
     Buyer buyer1 = buyerRepository.save(buyer);
     Buyer buyer2 = buyerRepository.save(buyer);
     var listOfAllBuyersInDb = buyerRepository.findAll();
     assertEquals(2, listOfAllBuyersInDb.size());
//     var deleteBuyer = buyerRepository.delete(buyer2);
     assertEquals(1, listOfAllBuyersInDb.size());
    }

    @Test
    void deleteAllTest(){
        buyerRepository.save(buyer);
        assertEquals(1, buyerRepository.findAll().size());
        buyerRepository.deleteAll();
        assertEquals(0, buyerRepository.findAll().size());
    }
}