package com.miaoshaproject.service;

import com.miaoshaproject.error.BussinessException;
import com.miaoshaproject.service.model.UserModel;

/**
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/4 23:24
 */
public interface UserService {
    /**
     * 查找用户对象
     *
     * @author 多宝
     * @since 2020/7/4 23:29
     * @param id 用户ID
     * @return com.miaoshaproject.dataobjects.UserDO
     **/
    UserModel getUserById(Integer id);

    /**
     * 用户注册
     *
     * @author 多宝
     * @since 2020/7/11 16:06
     * @param userModel  用户模型对象
     **/
    void register(UserModel userModel) throws BussinessException;


    /**
     * 验证用户登录的方法
     *
     * @author 多宝
     * @since 2020/7/12 21:54
     * @param telephone     用户手机号
     * @param encriptPassword      用户加密后的密码
     * @return com.miaoshaproject.service.model.UserModel
     **/
    UserModel validateLogin(String telephone, String encriptPassword) throws BussinessException;
}
