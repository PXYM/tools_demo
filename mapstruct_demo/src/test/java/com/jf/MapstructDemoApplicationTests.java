package com.jf;

import com.jf.dto.CarDTO;
import com.jf.dto.PayTypeNew;
import com.jf.dto.PersonDTO;
import com.jf.entity.Car;
import com.jf.entity.PayType;
import com.jf.entity.Person;
import com.jf.map.CarMapper;
import com.jf.map.PayTypeMapper;
import com.jf.map.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MapstructDemoApplicationTests {


    @Test
    public void test1(){
        Car wuling = new Car();
        wuling.setBrand("data");
        wuling.setPrice(6666.66);
        wuling.setOnMarket(true);
        wuling.setPayType(PayType.ALIPAY);

        Person person = new Person();
        person.setAge("12");
        person.setName("小红");

        wuling.setOwnerList(Collections.singletonList(person));
        CarDTO carDTO = CarMapper.INSTANCE.carToCarDTO(wuling);
        System.out.println("结果为：" + carDTO);
        //CarDTO(brand=data, price=6666.66, onSale=true,
        // ownerList=[PersonDTO(id=null, name=小红, age=12, cars=null)],
        // date=2023-05-19 21:19:60, payType=NETWORK)
    }

    @Test
    void test2(){
        PayType p1 = PayType.ALIPAY;

        PayTypeNew p2 = PayTypeMapper.INSTANCE.payTypeToPayTypeNew(p1);
        // 结果为：ALIPAY
        System.out.println("结果为：" + p2);
    }

    @Test
    void test3(){
        Person person = new Person();
        person.setName("小李");
        person.setAge("18");
//        person.setId("11111111");

        List<Car> cars = new ArrayList<>();
        Car car = new Car();
        car.setPrice(18888.00);
        car.setOnMarket(true);
        car.setBirthdate(new Date());
        car.setBrand("baoma");
        car.setPayType(PayType.ALIPAY);
        Car car2 = new Car();
        car2.setPrice(1888899.00);
        car2.setOnMarket(false);
        car2.setBirthdate(new Date());
        car2.setBrand("BYD");
        car2.setPayType(PayType.CARD_VISA);
        cars.add(car2);
        cars.add(car);
        PersonDTO personDTO = PersonMapper.INSTANCE.personToPersonDTO(person, cars);
        System.out.println(personDTO);
    }

}
