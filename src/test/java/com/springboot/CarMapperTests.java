package com.springboot;

import com.springboot.domain.Car;
import com.springboot.mapper.CarMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarMapperTests {
    @Resource(name = "carMapper")
    private CarMapper carMapper;

    @Test
    public void find() {
        for (Car car : carMapper.find()) {
            System.out.println(car.getName() + " " + car.getPrice() + " " + car.getCreateDate());
        }
    }

    @Test
    public void findById() {
        Car car = carMapper.findById(4);
        System.out.println(car.getName() + " " + car.getPrice() + " " + car.getCreateDate());
    }

    @Test
    public void findByParam() {
        List<Car> cars = carMapper.findByParam("", null, null);
        for (Car car : cars) {
            System.out.println(car.getName() + " " + car.getPrice() + " " + car.getCreateDate());
        }
    }

    @Test
    public void add() {
        Car car = new Car();
        car.setName("凯迪拉克");
        car.setPrice(7754.12);
        car.setCreateDate(new Date());
        System.out.println(carMapper.add(car));
    }

    @Test
    public void update() {
        Car car = new Car();
        car.setId(9);
        car.setName("凯迪拉克");
        car.setPrice(7754.14);
        car.setCreateDate(new Date());
        System.out.println(carMapper.modify(car));
    }

    @Test
    public void remove() {
        System.out.println(carMapper.remove(9));
    }
}
