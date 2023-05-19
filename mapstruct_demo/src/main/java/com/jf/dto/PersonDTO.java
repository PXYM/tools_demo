package com.jf.dto;

import lombok.Data;

import java.util.List;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/5/19 16:34
 */
@Data
public class PersonDTO {
    private String id;
    private String name;
    private String age;
    private List<CarDTO> cars;
}
