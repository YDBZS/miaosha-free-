package com.miaoshaproject;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dataobjects.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.miaoshaproject"})
@RestController
@MapperScan("com.miaoshaproject.dao")
public class App {

    @Resource
    private UserDOMapper userDOMapper;

    @RequestMapping(value = "/")
    public String home(){
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);

        if (null == userDO){
            return "用户对象不存在";
        }else {
            return userDO.getName();
        }

    }

    public static void main( String[] args ) {
        SpringApplication.run(App.class,args);
    }
}
