package com.jf.controller;

import com.jf.pojo.vo.ResultVO;
import com.jf.pojo.vo.UserVO;
import com.jf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author qjwyss
 * @date 2018/8/30
 * @description 用户控制类
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/export")
    public ResultVO<Void> exportUser(UserVO userVO, HttpServletResponse response) throws Exception {
//        return userService.export(userVO, response);
        return null;
    }

}
