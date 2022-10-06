package store.data.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private String name;
    private double price;
    private String category;
}
