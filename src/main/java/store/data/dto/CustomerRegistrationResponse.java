package store.data.dto;

import lombok.Data;

@Data
public class CustomerRegistrationResponse {
    private int userId;
    private String message;
    private int statusCode;

    @Override
    public String toString() {
        return "{\n" +
                "\tuserId: " + userId +"\n"+
                ",\t message: " + message + "\n"+
                ",\tstatusCode: " + statusCode +"\n"+
                '}';
    }
}
