package com.miaoshaproject.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 业务领域Model对象
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/4 23:37
 */
@ApiModel(value = "用户模型对象", description = "用户模型对象")
@Data
public class UserModel {


    @ApiModelProperty(value  = "用户主键ID", name = "id", required = true)
    private Integer id;

    @ApiModelProperty(value  = "用户名", name = "name", required = true)
    @NotNull(message = "用户名不能为空")   //对应的字段不能为控制空字符串不能为null
    private String name;

    @ApiModelProperty(value  = "性别", name = "gender", notes = "", allowableValues = "0 -> 女，1 -> 男", required = true)
    @NotNull(message = "性别不能不填")    //对应的字段不能为null
    private Byte gender;

    @ApiModelProperty(value  = "年龄", name = "age", notes = "", allowableValues = "", required = true)
    @NotNull(message = "年龄不能不填")
    @Min(value = 0, message = "年龄必须大于0")
    @Max(value = 150, message = "年龄必须小于150岁")
    private Integer age;

    @ApiModelProperty(value  = "用户手机号", name = "telephne", notes = "", allowableValues = "", required = true)
    @NotNull(message = "手机号不能为空")
    private String telephone;

    @ApiModelProperty(value  = "注册方式", name = "registerMode", notes = "", allowableValues = "", required = true)
    private String registrerMode;

    @ApiModelProperty(value  = "第三方ID", name = "thirdPartId", notes = "", allowableValues = "", required = true)
    private String thirdPartId;

    @ApiModelProperty(value  = "用户密码", name = "encrptPassword", notes = "", allowableValues = "", required = true)
    @NotNull(message = "密码不能为空")
    private String encrptPassword;
}
