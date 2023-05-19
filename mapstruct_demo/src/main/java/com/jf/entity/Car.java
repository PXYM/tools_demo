package com.jf.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/5/19 15:55
 */
@Data
public class Car {
    private String brand;
    private Double price;
    private Boolean onMarket;
    private Date birthdate;
    private List<Person> ownerList;
    private PayType payType;
}

