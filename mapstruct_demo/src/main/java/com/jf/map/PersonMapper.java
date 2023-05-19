package com.jf.map;

import com.jf.dto.CarDTO;
import com.jf.dto.PersonDTO;
import com.jf.entity.Car;
import com.jf.entity.Person;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/5/19 16:47
 */
@Mapper(imports = {UUID.class})
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);


    @Mapping(source = "person.id", target = "id", defaultExpression = "java(defaultId())")
    PersonDTO personToPersonDTO(Person person);

    @Mapping(source = "carList", target = "cars", qualifiedByName = "mapCar")
    @Mapping(source = "person.id", target = "id", defaultExpression = "java(defaultId())")
    PersonDTO personToPersonDTO(Person person, List<Car> carList);

    @Named("mapCar")
    default CarDTO mapCar(Car car){
        return CAR_MAPPER.carToCarDTO(car);
    }

    default String defaultId(){
        return UUID.randomUUID().toString();
    }

}
