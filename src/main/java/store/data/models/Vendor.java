package store.data.models;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Vendor extends User {
    private int id;
    private String storeName;
    private Set<String> storeAddress;

}
