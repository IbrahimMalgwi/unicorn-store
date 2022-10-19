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
        System.out.println("""
                Welcome to Unicorn Store. How may we be of service, today?
                1. Register
                2. Login
                3. Exit
                """);

        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1:
                System.out.println("Enter Your Email: ");
                String email = scanner.next();
                System.out.println("Enter your password: ");
                String password = scanner.next();
                System.out.println("Enter your phone-number: ");
                String phoneNumber = scanner.next();
                System.out.println("Enter your address: ");
                String address = scanner.next();

                CustomerRegistrationRequest request = new CustomerRegistrationRequest();
                request.setEmail(email);
                request.setPassword(password);
                request.setAddress(address);
                request.setPhoneNumber(phoneNumber);

                CustomerRegistrationResponse response = customerService.register(request);
        }



    }
}