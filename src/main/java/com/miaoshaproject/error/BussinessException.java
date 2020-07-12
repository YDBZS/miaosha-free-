package com.miaoshaproject.error;

/**
 * 统一异常处理
 * <p>
 *     包装器业务异常类实现
 * </p>
 *
 * @author 多宝
 * @since 2020/7/5 14:36
 */
public class BussinessException extends Exception implements CommonError {

    private CommonError commonError;

    //直接接受BussinessException参数的传参，用于构造业务异常
    public BussinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    //接受自定义errMsg的方式，构造业务异常
    public BussinessException(CommonError commonError, String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }


    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
