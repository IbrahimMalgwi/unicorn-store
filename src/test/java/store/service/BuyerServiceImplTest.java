package store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.dto.BuyerRegistrationRequest;
import store.data.models.Buyer;

import static org.junit.jupiter.api.Assertions.*;

class BuyerServiceImplTest {
    private final BuyerService buyerService = new BuyerServiceImpl();

    private  BuyerRegistrationRequest firstBuyerRegistrationRequest;
    private BuyerRegistrationRequest secondBuyerRegistrationRequest;


    @BeforeEach
    void setUp() {
        firstBuyerRegistrationRequest = new BuyerRegistrationRequest();
        firstBuyerRegistrationRequest.setEmail("judithibrahim@gmail.com");
        firstBuyerRegistrationRequest.setAddress("312, Herbert Macaulay Way, Sabo Yaba, Lagos");
        firstBuyerRegistrationRequest.setPassword("ILoveDaddy2022@");
        firstBuyerRegistrationRequest.setPhoneNumber("080122222345678");

        secondBuyerRegistrationRequest = new BuyerRegistrationRequest();
        secondBuyerRegistrationRequest.setEmail("joannaibrahim@gmail.com");
        secondBuyerRegistrationRequest.setAddress("313, Herbert Macaulay Way, Sabo Yaba");
        secondBuyerRegistrationRequest.setPassword("ILoveDaddy2022@");
        secondBuyerRegistrationRequest.setPhoneNumber("08012345679");

    }

    @Test
    void register() {
    }

    @Test
    void orderProduct() {
    }
}