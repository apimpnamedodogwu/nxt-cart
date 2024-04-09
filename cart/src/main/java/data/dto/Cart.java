package data.dto;

import lombok.Data;

import java.util.List;

@Data
public class Cart {

    private List<Item> items;
    private double total;
    private double discountedTotal;
    private double discount;

}
