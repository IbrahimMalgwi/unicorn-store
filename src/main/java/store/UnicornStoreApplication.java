package store;

import store.data.dto.CustomerRegistrationRequest;
import store.data.dto.CustomerRegistrationResponse;
import store.data.dto.LoginRequest;
import store.data.dto.LoginResponse;
import store.service.CustomerService;
import store.service.CustomerServiceImpl;
import store.service.ProductService;
import store.service.ProductServiceImpl;

import java.util.Scanner;


public class UnicornStoreApplication {
    private static final CustomerService customerService = new CustomerServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        prompt();
        int intent = getCustomerIntent();
        processCustomerRequest(intent);

    }

    private static void prompt(){

        System.out.println("""
                Welcome to unicorns-store. How may we help you?
                1.  register
                2.  login
                3.  place order
                """);
    }

    private static int getCustomerIntent(){
        return scanner.nextInt();
    }

    private static void processCustomerRequest(int intent){
        switch (intent){
            case 1:
                CustomerRegistrationRequest request = collectRegistrationDetails();
                CustomerRegistrationResponse response =customerService.register(request);
                System.out.println(response);
                break;
            case 2:
                LoginRequest loginRequest = collectLoginCredentials();
                LoginResponse loginResponse = customerService.login(loginRequest);
                System.out.println(loginResponse);
                break;
        }
    }

    private static LoginRequest collectLoginCredentials() {
        LoginRequest loginRequest = new LoginRequest();
        String customerEmail = collectEmailAddress();
        loginRequest.setEmail(customerEmail);
        String customerPassword = collectPassword();
        loginRequest.setPassword(customerPassword);
        return loginRequest;
    }

    private static String collectPassword() {
        System.out.println("Enter password: ");
        return scanner.next();
    }

    private static String collectEmailAddress() {
        System.out.println("Enter email: ");
        return scanner.next();
    }

    private static CustomerRegistrationRequest collectRegistrationDetails(){
        CustomerRegistrationRequest registrationRequest = new CustomerRegistrationRequest();
        String customerEmail = collectEmailAddress();
        registrationRequest.setEmail(customerEmail);
        String customerPassword = collectPassword();
        registrationRequest.setPassword(customerPassword);
        System.out.println("Enter phoneNumber: ");
        registrationRequest.setPhoneNumber(scanner.next());
        System.out.println("Enter address: ");
        registrationRequest.setAddress(scanner.next());
        return registrationRequest;
    }
}