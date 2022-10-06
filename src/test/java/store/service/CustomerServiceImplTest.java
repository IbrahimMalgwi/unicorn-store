package store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.dto.AddProductRequest;
import store.data.dto.CustomerRegistrationRequest;
import store.data.dto.CustomerRegistrationResponse;
import store.data.dto.ProductPurchaseRequest;
import store.exception.CustomerRegistrationException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    private final CustomerService customerService = new CustomerServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private CustomerRegistrationRequest firstBuyerRegisterRequest;
    private CustomerRegistrationRequest secondBuyerRegisterRequest;
    private ProductPurchaseRequest productPurchaseRequest;
    private AddProductRequest addProductRequest;



    @BeforeEach
    void setUp() {
        firstBuyerRegisterRequest = new CustomerRegistrationRequest();
        firstBuyerRegisterRequest.setEmail("judithibrahim@gmail.com");
        firstBuyerRegisterRequest.setAddress("312, Herbert Macaulay Way, Sabo Yaba, Lagos");
        firstBuyerRegisterRequest.setPassword("ILoveDaddy2022@");
        firstBuyerRegisterRequest.setPhoneNumber("08012345678");

        secondBuyerRegisterRequest = new CustomerRegistrationRequest();
        secondBuyerRegisterRequest.setEmail("joannaibrahim@gmail.com");
        secondBuyerRegisterRequest.setAddress("313, Herbert Macaulay Way, Sabo Yaba");
        secondBuyerRegisterRequest.setPassword("ILoveDaddy-2022@");
        secondBuyerRegisterRequest.setPhoneNumber("08012345679");

        productPurchaseRequest = new ProductPurchaseRequest();
        productPurchaseRequest.setProductId(1);
        productPurchaseRequest.setQuantity(2);

        addProductRequest = new AddProductRequest();
        addProductRequest.setCategory("BEVERAGES");
        addProductRequest.setName("Coke");
        addProductRequest.setPrice(150);
    }


    @Test
    void register() {
        CustomerRegistrationResponse response = customerService.register(firstBuyerRegisterRequest);
        System.out.println(response);
        assertNotNull(response);
        assertEquals(response.getStatusCode(), 201);
    }

    @Test
    void userWithInvalidDetailsGetsExceptionWhenRegisteringTest(){
        assertThrows(CustomerRegistrationException.class, ()-> customerService.register(secondBuyerRegisterRequest));
    }

    @Test
    void orderProduct() {
        var addProductResponse = productService.addProduct(addProductRequest);
        productService.addProduct(addProductRequest);
        String response = customerService.orderProduct(productPurchaseRequest);
        assertNotNull(response);
    }
}