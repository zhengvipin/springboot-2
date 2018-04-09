package com.springboot.controller;

import com.springboot.domain.Car;
import com.springboot.domain.CustomType;
import com.springboot.service.CarService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Restful风格 控制器
 * 1.前后端分离，请求方式不同 ——查询：get || 新增：post || 修改：put || 删除：delete
 * 2.传参注解—— 个别参数：{} && @PathVariable || 对象：@PathVariable
 * 3.domain实体类中时间格式转换注解@DateTimeFormat && @JsonFormat
 *
 * @author Zheng
 */
@RestController
@CrossOrigin(origins = "http://127.0.0.1:8080", maxAge = 3600) // 跨域
@RequestMapping(value = "/car")
public class CarRestController {
    @Resource(name = "carService")
    private CarService carService;

    /**
     * 查询全部
     *
     * @return
     */
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ResponseEntity<?> getCars() {

        List<Car> cars = carService.find();
        if (cars.isEmpty()) {
            return new ResponseEntity<>(new CustomType(400, "查无数据！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "cars/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCar(@PathVariable("id") Integer id) {
        Car car = carService.find(id);
        if (car == null) {
            return new ResponseEntity<>(new CustomType(400, "ID没有匹配的结果！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /**
     * 模糊查询
     *
     * @param name
     * @param beginDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "cars/query", method = RequestMethod.POST)
    public ResponseEntity<?> queryCars(@RequestParam("name") String name,
                                       @DateTimeFormat(pattern = "yyyy-MM-dd")
                                       @RequestParam("beginDate") Date beginDate,
                                       @DateTimeFormat(pattern = "yyyy-MM-dd")
                                       @RequestParam("endDate") Date endDate) {
        List<Car> cars = carService.find(name, beginDate, endDate);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(new CustomType(400, "查无数据！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     * 新增
     *
     * @param car
     * @return postman要在body-raw中测试 格式改为json
     */
    @RequestMapping(value = "cars", method = RequestMethod.POST)
    public ResponseEntity<?> addCar(@PathVariable Car car) { // 使用@RequestBody注解将json参数转换为对象
        CustomType customType = new CustomType(400, "新增失败!");
        int count = carService.add(car);
        if (count > 0) {
            customType = new CustomType(200, "新增成功");
        }
        return new ResponseEntity<>(customType, HttpStatus.OK);
    }

    /**
     * 修改
     *
     * @param car
     * @return
     */
    @RequestMapping(value = "cars", method = RequestMethod.PUT)
    public ResponseEntity<?> modify(@RequestBody Car car) {
        int count = carService.modify(car);
        CustomType customType = new CustomType(400, "修改失败!");

        if (count > 0) {
            customType = new CustomType(200, "修改成功");
        }
        return new ResponseEntity<>(customType, HttpStatus.OK);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> remove(@PathVariable("id") Integer id) {
        int count = carService.remove(id);
        CustomType customType = new CustomType(400, "删除失败!ID:" + id);

        if (count > 0) {
            customType = new CustomType(200, "删除成功");
        }
        return new ResponseEntity<>(customType, HttpStatus.OK);
    }
}

