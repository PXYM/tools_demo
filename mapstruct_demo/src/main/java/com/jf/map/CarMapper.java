package com.jf.map;

import com.jf.dto.CarDTO;
import com.jf.dto.PayTypeNew;
import com.jf.entity.Car;
import com.jf.entity.PayType;
import com.jf.entity.Person;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PrivateKey;

/**
 * @author 86136
 * @version 1.0
 * @description TODO
 * @date 2023/5/19 15:57
 */
@Mapper
public interface CarMapper {

    String DATE_PATTER = "yyyy-MM-dd HH:mm:SS";
    PayTypeMapper PAY_TYPE_MAPPER = Mappers.getMapper(PayTypeMapper.class);

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "car.onMarket", target = "onSale", defaultValue = "true")
    @Mapping(source = "car.birthdate", target = "date"
            , dateFormat = DATE_PATTER
            , defaultExpression =
            "java(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern(DATE_PATTER)))")
    @Mapping(source = "car.payType", target = "payType", qualifiedByName = "mapPayType")
    CarDTO carToCarDTO(Car car);

    @Named("mapPayType")
    default PayTypeNew map(PayType payType){
        return PAY_TYPE_MAPPER.payTypeToPayTypeNew(payType);
    }

}
