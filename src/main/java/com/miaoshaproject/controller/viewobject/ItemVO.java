package com.miaoshaproject.controller.viewobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 用于展示给前端展示的对象
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/26 14:32
 */
@Data
@ApiModel(value = "尚品VO", description = "用于展示前端用户的对象封装")
public class ItemVO {

    @ApiModelProperty(value = "商品主键ID", name = "id", required = true)
    private Integer id;

    @ApiModelProperty(value = "商品名称", name = "title", required = true)
    private String title;

    @ApiModelProperty(value = "商品价格", name = "price", required = true)
    private BigDecimal price;

    @ApiModelProperty(value = "商品的库存", name = "stock", required = true)
    private Integer stock;

    @ApiModelProperty(value = "商品的描述", name = "description", required = true)
    private String description;

    @ApiModelProperty(value = "销量", name = "sales", required = true)
    private Integer sales;

    @ApiModelProperty(value = "商品描述图片 的url", name = "imgUrl", required = true)
    private String imgUrl;

}
