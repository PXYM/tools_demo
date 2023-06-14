package com.jf.service.impl;

import com.jf.mapper.UserMapper;
import com.jf.pojo.vo.ResultVO;
import com.jf.pojo.vo.UserVO;
import com.jf.service.UserService;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author qjwyss
 * @date 2018/8/30
 * @description 用户SERVICEIMPL
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

}
