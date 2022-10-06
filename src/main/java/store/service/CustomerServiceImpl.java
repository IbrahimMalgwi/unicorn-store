package store.service;

import store.data.dto.CustomerRegistrationRequest;
import store.data.dto.CustomerRegistrationResponse;
import store.data.dto.ProductPurchaseRequest;
import store.data.models.Customer;
import store.data.repositories.CustomerRepository;
import store.data.repositories.CustomerRepositoryImpl;
import store.exception.CustomerRegistrationException;
import store.utils.validators.UserDetailsValidator;

import java.util.Set;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest) {

        if (!UserDetailsValidator.isValidEmailAddress(registrationRequest.getEmail())) throw new CustomerRegistrationException(String.format("email %s is invalid", registrationRequest.getEmail()));

        if (!UserDetailsValidator.isValidPhoneNumber(registrationRequest.getPhoneNumber())) throw new CustomerRegistrationException(String.format("phone number %s is invalid", registrationRequest.getPhoneNumber()));

        if (!UserDetailsValidator.isValidPassword(registrationRequest.getPassword())) throw new CustomerRegistrationException(String.format("password %s is weak", registrationRequest.getPassword()));

        Customer customer = buildBuyer(registrationRequest);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerRegistrationResponse response = buildBuyerRegistrationResponse(savedCustomer);
        return response;
    }

    private CustomerRegistrationResponse buildBuyerRegistrationResponse(Customer savedCustomer) {
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response.setMessage("User registration successful");
        response.setStatusCode(201);
        response.setUserId(savedCustomer.getId());

        return response;
    }

    private Customer buildBuyer(CustomerRegistrationRequest registrationRequest) {
        Customer customer = new Customer();
        customer.setEmail(registrationRequest.getEmail());
        customer.setPassword(registrationRequest.getPassword());
        Set<String> buyersAddressList = customer.getDeliveryAddresses();
        buyersAddressList.add(registrationRequest.getAddress());
        customer.setPhoneNumber(registrationRequest.getPhoneNumber());
        return customer;
    }

    @Override
    public String orderProduct(ProductPurchaseRequest productPurchaseRequest) {

        return null;
    }
}
