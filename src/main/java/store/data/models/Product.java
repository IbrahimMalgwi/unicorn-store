package store.data.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Product {
    private int Id;
    private String name;
    private BigDecimal price;
    private Category category;
    private int quantity;


}
