package com.miaoshaproject.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    private String name;

    @ApiModelProperty(value  = "性别", name = "gender", notes = "", allowableValues = "0 -> 女，1 -> 男", required = true)
    private Byte gender;

    @ApiModelProperty(value  = "年龄", name = "age", notes = "", allowableValues = "", required = true)
    private Integer age;

    @ApiModelProperty(value  = "用户手机号", name = "telephne", notes = "", allowableValues = "", required = true)
    private String telephone;

    @ApiModelProperty(value  = "注册方式", name = "registerMode", notes = "", allowableValues = "", required = true)
    private String registrerMode;

    @ApiModelProperty(value  = "第三方ID", name = "thirdPartId", notes = "", allowableValues = "", required = true)
    private String thirdPartId;

    @ApiModelProperty(value  = "用户密码", name = "encrptPassword", notes = "", allowableValues = "", required = true)
    private String encrptPassword;
}
