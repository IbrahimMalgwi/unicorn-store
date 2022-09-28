package store.service;

import store.data.dto.BuyerRegistrationRequest;
import store.data.dto.BuyerRegistrationResponse;
import store.data.dto.ProductPurchaseRequest;
import store.data.models.Buyer;
import store.data.models.User;
import store.data.repositories.BuyerRepository;
import store.data.repositories.BuyerRepositoryImpl;
import store.exception.BuyerRegistrationException;
import store.utils.validators.UserDetailsValidator;

import java.util.Set;

public class BuyerServiceImpl implements BuyerService{
    private BuyerRepository buyerRepository = new BuyerRepositoryImpl();

    @Override
    public BuyerRegistrationResponse register(BuyerRegistrationRequest registrationRequest) {

        if (!UserDetailsValidator.isValidEmailAddress(registrationRequest.getEmail())) throw new BuyerRegistrationException(String.format("email %s is invalid", registrationRequest.getEmail()));

        if (!UserDetailsValidator.isValidPhoneNumber(registrationRequest.getPhoneNumber())) throw new BuyerRegistrationException(String.format("phone number %s is invalid", registrationRequest.getPhoneNumber()));

        if (!UserDetailsValidator.isValidPassword(registrationRequest.getPassword())) throw new BuyerRegistrationException(String.format("password %s is weak", registrationRequest.getPassword()));

        Buyer buyer = buildBuyer(registrationRequest);

        Buyer savedBuyer = buyerRepository.save(buyer);

        BuyerRegistrationResponse response = buildBuyerRegistrationResponse(savedBuyer);
        return response;
    }

    private BuyerRegistrationResponse buildBuyerRegistrationResponse(Buyer savedBuyer) {
        BuyerRegistrationResponse response = new BuyerRegistrationResponse();
        response.setMessage("User registration successful");
        response.setStatusCode(201);
        response.setUserId(savedBuyer.getId());

        return response;
    }

    private Buyer buildBuyer(BuyerRegistrationRequest registrationRequest) {
        Buyer buyer = new Buyer();
        buyer.setEmail(registrationRequest.getEmail());
        buyer.setPassword(registrationRequest.getPassword());
        Set<String> buyersAddressList = buyer.getDeliveryAddresses();
        buyersAddressList.add(registrationRequest.getAddress());
        buyer.setPhoneNumber(registrationRequest.getPhoneNumber());
        return buyer;
    }

    @Override
    public String orderProduct(ProductPurchaseRequest productPurchaseRequest) {
        return null;
    }
}
