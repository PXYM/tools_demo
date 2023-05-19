package com.jf.dto;

import lombok.Data;

import java.util.List;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/5/19 15:56
 */
@Data
public class CarDTO {
    private String brand;
    private Double price;
    private Boolean onSale;
    private List<PersonDTO> ownerList;
    private String date;
    private PayTypeNew payType;
}
