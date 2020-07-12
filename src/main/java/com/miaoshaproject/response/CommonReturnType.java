package com.miaoshaproject.response;

/**
 * 统一数据返回结果
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/5 14:07
 */
public class CommonReturnType {

    //表明对应请求的返回处理结果 "success" 或 "fail"
    private String status;

    //若status = success，则data内返回前端需要的json数据
    //若status = fail，则data内使用通用的错误码格式
    private Object data;


    //定义一个通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result, "success");
    }

    public CommonReturnType() {
        this.status = "success";
    }

    public static CommonReturnType create(Object result, String success){
        CommonReturnType type = new CommonReturnType();
        type.setData(result);
        type.setStatus(success);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
