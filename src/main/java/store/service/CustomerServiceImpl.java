package store.service;

import store.data.dto.*;
import store.data.models.Customer;
import store.data.models.Product;
import store.data.repositories.CustomerRepository;
import store.data.repositories.CustomerRepositoryImpl;
import store.exception.CustomerRegistrationException;
import store.exception.StoreException;
import store.utils.validators.UserDetailsValidator;

import java.util.Set;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest) {

        if (!UserDetailsValidator.isValidEmailAddress(registrationRequest.getEmail())) throw new CustomerRegistrationException(String.format("email %s is invalid", registrationRequest.getEmail()));

        if (!UserDetailsValidator.isValidPhoneNumber(registrationRequest.getPhoneNumber())) throw new CustomerRegistrationException(String.format("phone number %s is invalid", registrationRequest.getPhoneNumber()));

        if (!UserDetailsValidator.isValidPassword(registrationRequest.getPassword())) throw new CustomerRegistrationException(String.format("password %s not valid", registrationRequest.getPassword()));

        Customer customer = buildBuyer(registrationRequest);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerRegistrationResponse response = buildBuyerRegistrationResponse(savedCustomer);
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Customer foundCustomer = customerRepository.findByEmail(loginRequest.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        if (foundCustomer.getPassword().equals(loginRequest.getPassword())){
            loginResponse.setMessage("successful login");
            return loginResponse;
        }loginResponse.setMessage("authentication failed");
        return loginResponse;
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
        Customer customer = customerRepository.findById(productPurchaseRequest.getCustomerId());
        Product product = productService.getProductById(productPurchaseRequest.getProductId());
        if (product == null) throw new StoreException("product not found");
        if (product.getQuantity()>= productPurchaseRequest.getQuantity()){
            customer.getOrders().add(product);
            customerRepository.save(customer);
            return "order successful";
        } else {
            throw new StoreException("order quantity larger than available quantity");
        }
    }
}
