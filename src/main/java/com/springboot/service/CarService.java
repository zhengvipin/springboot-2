package com.springboot.service;

import com.springboot.domain.Car;
import com.springboot.mapper.CarMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class CarService {
    @Resource(name = "carMapper")
    private CarMapper carMapper;

    public List<Car> find() {
        return carMapper.find();
    }

    public Car find(Integer id) {
        return carMapper.findById(id);
    }

    public List<Car> find(String name, Date beginDate, Date endDate) {
        return carMapper.findByParam(name, beginDate, endDate);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int add(Car car) {
        return carMapper.add(car);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int modify(Car car) {
        return carMapper.modify(car);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int remove(Integer id) {
        return carMapper.remove(id);
    }
}
