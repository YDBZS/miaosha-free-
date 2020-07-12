package com.miaoshaproject.error;

/**
 * 统一异常处理
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/5 14:21
 */
public interface CommonError {

    public int getErrCode();

    public String getErrMsg();

    public CommonError setErrMsg(String errMsg);
}
