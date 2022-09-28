package store.data.models;

import lombok.Data;

@Data
public class User {
    private int Id;
    private String email;
    private String password;
    private String phoneNumber;
}
