package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BussinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

/**
 * 用户相关COntroller
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/4 22:23
 */
@Api(value = "用户相关Controller接口", tags = {"用户相关Controller接口"})
@RestController
@RequestMapping("user")
public class UserController extends BaseController{

    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = {CONTEN_TYPE_FORMED})
    @ApiOperation(value = "用户登录接口", httpMethod = "POST")
    public CommonReturnType login(
        @RequestParam(value = "telephone")
            String telephone,
        @RequestParam(value = "password")
            String password
    ) throws Exception{
        //入参校验
        if (StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password)){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //用户登录服务，用来校验用户登录是否合法
        UserModel userModel = userService.validateLogin(telephone, this.encodeByMd5(password));

        //将登录凭证加入到用户登录成功的Session内
        this.request.getSession().setAttribute("IS_LOGIN",true);
        this.request.getSession().setAttribute("LOGIN_USER",userModel);

        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = {CONTEN_TYPE_FORMED})
    @ApiOperation(value = "用户注册接口", httpMethod = "POST")
    public CommonReturnType register(
            @RequestParam(name = "telephone")
                String telephone,
            /*@RequestParam(name = "otpCode")
                String otpCode,*/
            @RequestParam(name = "name")
                String name,
            @RequestParam(value = "gender")
                Integer gender,
            @RequestParam(value = "age")
                Integer age,
            @RequestParam(value = "password")
                String password
    )throws Exception{
        //验证手机号和对应的otpCode相符合
        /*String inSessionOtpCode = (String) this.request.getSession().getAttribute("telephone");
        if (!org.apache.commons.lang3.StringUtils.equals(otpCode,inSessionOtpCode)){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不符合");
        }*/
        //用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(Byte.valueOf(String.valueOf(gender)));
        userModel.setAge(age);
        userModel.setTelephone(telephone);
        userModel.setRegistrerMode("byphone");
        userModel.setEncrptPassword(this.encodeByMd5(password));

        userService.register(userModel);

        return new CommonReturnType();
    }

    public String encodeByMd5(String str) throws Exception{
        //确定一个计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder encoder = new BASE64Encoder();
        //加密字符串
        return encoder.encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
    }

    @ApiOperation(value = "用户获取Otp验证码的接口", httpMethod = "POST")
    @RequestMapping(value = "/getOtp", method = RequestMethod.POST, consumes = {CONTEN_TYPE_FORMED})
    public CommonReturnType getOtp(@RequestParam(name = "telephone") String telephone){
        //需要按照一定的规则生成Otp验证码
        Random random = new Random();
        int i = random.nextInt(99999);
        i += 10000;
        String otpCode = String.valueOf(i);

        //将Otp验证码通对应用户的手机号关联，使用httpSession的方式绑定他的手机号与otpCode
        request.getSession().setAttribute(telephone,otpCode);


        //将Otp验证码通过短信通道发送给用户(省略)

        System.out.println("telephone = " + telephone + "，otpCode = " + otpCode);

        return CommonReturnType.create(otpCode);
    }


    @ApiOperation(value = "获取用户对象信息", httpMethod = "POST", response = UserVO.class)
    @RequestMapping("/getUser")
    public CommonReturnType getUser(
            @RequestParam(name = "id")
            @ApiParam(value = "用户数据主键ID", name = "id", required = true)
                Integer id
    ) throws BussinessException {
        //1.调用Service服务获取对应ID的用户对象并返回给前端
        UserModel user = userService.getUserById(id);

        //若获取的对应用户信息不存在
        if (StringUtils.isEmpty(user)){
            throw new BussinessException(EmBusinessError.USER_NOT_EIXST);
        }

        UserVO userVO = convertFromModel(user);

        //返回通用对象
        return CommonReturnType.create(userVO);
    }

    /**
     * 将核心领域对象模型转化为可供UI使用的ViewObject
     *
     * @author 多宝
     * @since 2020/7/5 0:36
     * @param userModel 业务处理核心领域对象模型
     * @return com.miaoshaproject.controller.viewobject.UserVO
     **/
    private UserVO convertFromModel(UserModel userModel){
        if (StringUtils.isEmpty(userModel)){
            return null;
        }
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(userModel,vo);

        return vo;
    }





}
