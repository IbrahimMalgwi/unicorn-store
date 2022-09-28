package store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.dto.BuyerRegistrationRequest;
import store.data.dto.BuyerRegistrationResponse;
import store.data.models.Buyer;

import static org.junit.jupiter.api.Assertions.*;

class BuyerServiceImplTest {
    private final BuyerService buyerService = new BuyerServiceImpl();

    private  BuyerRegistrationRequest firstBuyerRegisterRequest;
    private BuyerRegistrationRequest secondBuyerRegisterRequest;


    @BeforeEach
    void setUp() {
        firstBuyerRegisterRequest = new BuyerRegistrationRequest();
        firstBuyerRegisterRequest.setEmail("judithibrahim@gmail.com");
        firstBuyerRegisterRequest.setAddress("312, Herbert Macaulay Way, Sabo Yaba, Lagos");
        firstBuyerRegisterRequest.setPassword("ILoveDaddy2022@");
        firstBuyerRegisterRequest.setPhoneNumber("080122222345678");

        secondBuyerRegisterRequest = new BuyerRegistrationRequest();
        secondBuyerRegisterRequest.setEmail("joannaibrahim@gmail.com");
        secondBuyerRegisterRequest.setAddress("313, Herbert Macaulay Way, Sabo Yaba");
        secondBuyerRegisterRequest.setPassword("ILoveDaddy2022@");
        secondBuyerRegisterRequest.setPhoneNumber("08012345679");
    }

    @Test
    void register() {
        BuyerRegistrationResponse response = buyerService.register(firstBuyerRegisterRequest);
        assertNotNull(response);
        assertEquals(response.getStatusCode(), 201);
    }

    @Test
    void orderProduct() {
    }
}