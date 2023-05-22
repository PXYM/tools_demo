package com.jf.controller;

import com.jf.entity.User;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/5/21 14:23
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    //简单数据类型校验
    @RequestMapping("/delete")
    public String delete(@NotBlank(message = "id不能为空") String id){
        System.out.println("delete..." + id);
        return "OK";
    }

    //对象属性校验
    @RequestMapping("/save")
    public String save(@Valid User user){
        System.out.println("save..." + user);
        return "OK";
    }
}
