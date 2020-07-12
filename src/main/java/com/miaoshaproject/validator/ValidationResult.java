package com.miaoshaproject.validator;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 校验返回结果
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/12 23:00
 */
@Data
public class ValidationResult {
    //校验结果是否有错
    private boolean hasError = false;

    //存放错误信息的Map
    private Map<String,String> errMsgMap = new HashMap<>();


    //实现通用的通过格式化信息获取错误结果的MSG方法
    public String getErrMsg(){
        return StringUtils.join(errMsgMap.values().toArray(), ",");
    }

}
