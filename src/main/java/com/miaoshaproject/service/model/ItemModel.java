package com.miaoshaproject.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 商品模型
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/18 21:57
 */
@Data
@ApiModel(value = "商品模型对象", description = "商品模型对象")
public class ItemModel {

    @ApiModelProperty(value = "商品主键ID", name = "id", required = true)
    private Integer id;

    @ApiModelProperty(value = "商品名称", name = "title", required = true)
    @NotNull(message = "商品名称不能为空")
    private String title;

    @ApiModelProperty(value = "商品价格", name = "price", required = true)
    @NotNull(message = "商品价格不能为空")
    @Min(value = 0, message = "商品价格必须大于0")
    private BigDecimal price;

    @ApiModelProperty(value = "商品的库存", name = "stock", required = true)
    @NotNull(message = "库存不能不填")
    private Integer stock;

    @ApiModelProperty(value = "商品的描述", name = "description", required = true)
    @NotNull(message = "商品描述信息不能为空")
    private String description;

    @ApiModelProperty(value = "销量", name = "sales", required = true)
    private Integer sales;

    @ApiModelProperty(value = "商品描述图片 的url", name = "imgUrl", required = true)
    @NotNull(message = "商品图片信息不能为空")
    private String imgUrl;


}
