package store.service;

import store.data.dto.BuyerRegistrationRequest;
import store.data.dto.BuyerRegistrationResponse;
import store.data.dto.ProductPurchaseRequest;
import store.data.models.Buyer;
import store.data.repositories.BuyerRepository;
import store.data.repositories.BuyerRepositoryImpl;

import java.util.Set;

public class BuyerServiceImpl implements BuyerService{
    private BuyerRepository buyerRepository = new BuyerRepositoryImpl();

    @Override
    public BuyerRegistrationResponse register(BuyerRegistrationRequest registrationRequest) {
        //create buyer
        Buyer buyer = new Buyer();
        buyer.setEmail(registrationRequest.getEmail());
        buyer.setPassword(registrationRequest.getPassword());
        Set<String> buyersAddressList = buyer.getDeliveryAddresses();
        buyersAddressList.add(registrationRequest.getAddress());
        buyer.setPhoneNumber(registrationRequest.getPhoneNumber());

        //save
        Buyer savedBuyer = buyerRepository.save(buyer);

        //create register
        BuyerRegistrationResponse response = new BuyerRegistrationResponse();
        response.setMessage("User regisration successful");
        response.setStatusCode(201);
        response.setUserId(savedBuyer.getId());

        return response;
    }

    @Override
    public String orderProduct(ProductPurchaseRequest productPurchaseRequest) {
        return null;
    }
}
