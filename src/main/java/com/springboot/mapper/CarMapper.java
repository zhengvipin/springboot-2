package com.springboot.mapper;

import com.springboot.domain.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 映射器接口
 *
 * @author Zheng
 */
@Repository
public interface CarMapper {

    @Select("select id,name,price,createDate from car")
    List<Car> find();

    @Select("select id,name,price,createDate from car where id=#{id}")
    Car findById(Integer id);

    List<Car> findByParam(@Param("name") String name,
                          @Param("beginDate") Date beginDate,
                          @Param("endDate") Date endDate);

    @Insert("insert into car(name,price,createDate) values(#{name},#{price},#{createDate})")
    int add(Car car);

    @Update("update car set name=#{name},price=#{price},createDate=#{createDate} where id=#{id}")
    int modify(Car car);

    @Delete("delete from car where id=#{id}")
    int remove(Integer id);
}
