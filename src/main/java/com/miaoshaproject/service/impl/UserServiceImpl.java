package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dao.UserPasswordDOMapper;
import com.miaoshaproject.dataobjects.UserDO;
import com.miaoshaproject.dataobjects.UserPasswordDO;
import com.miaoshaproject.error.BussinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/4 23:25
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDOMapper userDoMapper;

    @Resource
    private UserPasswordDOMapper userPasswordDoMapper;

    @Resource
    private ValidatorImpl validator;


    @Override
    public UserModel getUserById(Integer id) {
        //调用userDoMapper获取到对应用户的dataobject
        UserDO userDO = userDoMapper.selectByPrimaryKey(id);
        if (StringUtils.isEmpty(userDO)){
            return null;
        }
        //通过用户ID获取到对应的用户加密密码信息
        UserPasswordDO userPasswordDO = userPasswordDoMapper.selectByUserId(userDO.getId());
        return convertFromDateObject(userDO, userPasswordDO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void register(UserModel userModel) throws BussinessException {
        if (StringUtils.isEmpty(userModel)){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        /*if (org.apache.commons.lang3.StringUtils.isEmpty(userModel.getName())
                || StringUtils.isEmpty(userModel.getGender())
                || StringUtils.isEmpty(userModel.getAge())
                || StringUtils.isEmpty(userModel.getTelephone())
        ){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }*/
        ValidationResult result = validator.validator(userModel);
        if (result.isHasError()){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        //实现model转DataObject方法
        UserDO userDO = convertFromModel(userModel);

        try {
            userDoMapper.insertSelective(userDO);
        } catch (DuplicateKeyException exception) {
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号已重复注册");
        }

        userModel.setId(userDO.getId());

        UserPasswordDO passwordDO = convertPasswordFromModel(userModel);

        userPasswordDoMapper.insertSelective(passwordDO);
    }

    @Override
    public UserModel validateLogin(String telephone, String encriptPassword) throws BussinessException {
        //通过用户手机号获取用户信息
        UserDO userDO = userDoMapper.selectByTelephone(telephone);
        if (StringUtils.isEmpty(userDO)){
            throw new BussinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO passwordDO = userPasswordDoMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDateObject(userDO, passwordDO);
        //比对用户信息内加密的密码是否和传输进来的密码相匹配
        if (!org.apache.commons.lang3.StringUtils.equals(encriptPassword,userModel.getEncrptPassword())){
            throw new BussinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        return userModel;
    }


    /**
     * 转换passWord
     *
     * @author 多宝
     * @since 2020/7/11 16:33
     * @param userModel   用户Model对象
     * @return com.miaoshaproject.dataobjects.UserPasswordDO
     **/
    public UserPasswordDO convertPasswordFromModel(UserModel userModel){
        if (StringUtils.isEmpty(userModel)){
            return null;
        }
        UserPasswordDO passwordDO = new UserPasswordDO();
        passwordDO.setEncriptPassword(userModel.getEncrptPassword());
        passwordDO.setUserId(userModel.getId());
        return passwordDO;
    }

    /**
     * model转成DO的方法
     *
     * @author 多宝
     * @since 2020/7/11 16:17
     * @param userModel   用户Model对象
     * @return com.miaoshaproject.dataobjects.UserDO
     **/
    private UserDO convertFromModel(UserModel userModel) {
        if (StringUtils.isEmpty(userModel)){
            return null;
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;

    }

    /**
     * 用过映射Bean组装nidel对象
     *
     * @author 多宝
     * @since 2020/7/4 23:54
     * @param userDO           用户BO
     * @param userPasswordDO   密码BO
     * @return com.miaoshaproject.service.model.UserModel   用户模型对象
     **/
    private UserModel convertFromDateObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if (StringUtils.isEmpty(userDO)){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if (StringUtils.isEmpty(userPasswordDO)){
            return null;
        }
        userModel.setEncrptPassword(userPasswordDO.getEncriptPassword());
        return userModel;
    }
}
