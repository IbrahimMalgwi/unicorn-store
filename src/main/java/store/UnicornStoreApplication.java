package store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("""
                Welcome to Unicorn Store. How may we be of service, today?
                1. Register
                2. Login
                3. Exit
                """);

        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1:
                displayRegistrationHeader();
                CustomerRegistrationRequest request = collectCustomerRegistrationDetails();
                CustomerRegistrationResponse response = customerService.register(request);
                String jsonResponse = objectMapper.writeValueAsString(response);
                System.out.println(jsonResponse);

            case 2:
                displayLoginHeader();
                LoginRequest loginRequest = collectCustomerLoginDetails();
                LoginResponse loginResponse = customerService.login(loginRequest);
                String jsonLoginResponse = objectMapper.writeValueAsString(loginResponse);
                System.out.println(jsonLoginResponse);
        }
    }

    private static CustomerRegistrationRequest collectCustomerRegistrationDetails(){
        System.out.println("Enter Your Email: ");
        String email = scanner.next();

        System.out.println("Enter your password: ");
        String password = scanner.next();
        System.out.println("Password::" + password);

        System.out.println("Enter your phone-number: ");
        String phoneNumber = scanner.next();
        scanner.nextLine();

        System.out.println("Enter your address: ");
        String address = scanner.nextLine();

        return buildCustomerRegistrationRequest(email, password, phoneNumber, address);
    }

    private static CustomerRegistrationRequest buildCustomerRegistrationRequest(String email, String password, String phoneNumber, String address){
        CustomerRegistrationRequest request = new CustomerRegistrationRequest();
        request.setEmail(email);
        request.setPassword(password);
        request.setAddress(address);
        request.setPhoneNumber(phoneNumber);
        return request;
    }

    private static LoginRequest collectCustomerLoginDetails(){
        System.out.println("Enter Your Email: ");
        String email = scanner.next();
        System.out.println("Enter your password: ");
        String password = scanner.next();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        return loginRequest;
    }

    private static void displayRegistrationHeader(){
        System.out.println("""
                            RESGISTER
                        """);

    }

    private static void displayLoginHeader(){
        System.out.println("""
                            LOGIN PAGE
                        """);
    }
}