package com.miaoshaproject.controller.viewobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 展示层对象
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/5 0:30
 */
@ApiModel(value = "用户展示层对象", description = "用户展示VO")
@Data
public class UserVO {
    @ApiModelProperty(value  = "用户主键ID", name = "id", required = true)
    private Integer id;

    @ApiModelProperty(value  = "用户名", name = "name", required = true)
    private String name;

    @ApiModelProperty(value  = "性别", name = "gender", example = "0 -> 女，1 -> 男", allowableValues = "", required = true)
    private Byte gender;

    @ApiModelProperty(value  = "年龄", name = "age", notes = "", allowableValues = "", required = true)
    private Integer age;

    @ApiModelProperty(value  = "用户手机号", name = "telephne", notes = "", allowableValues = "", required = true)
    private String telephone;

}
