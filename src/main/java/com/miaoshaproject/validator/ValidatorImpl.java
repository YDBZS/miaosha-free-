package com.miaoshaproject.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 获取错误信息
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/12 23:14
 */
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    //实现校验方法并返回校验结果
    public ValidationResult validator(Object bean){
        ValidationResult result = new ValidationResult();
        //如果某个bean里面的某个字段违背了字段上面注解的定义规则的话，set集合就会存放对应的值
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if (constraintViolationSet.size() > 0){
            //有错误
            result.setHasError(true);
            //遍历对应的错误
            constraintViolationSet.forEach(objectConstraintViolation -> {
                //首先获得异常的errMsg
                String message = objectConstraintViolation.getMessage();
                //获取发生错误得字段
                String property = objectConstraintViolation.getPropertyPath().toString();
                result.getErrMsgMap().put(property,message);;

            });
        }
        return result;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
