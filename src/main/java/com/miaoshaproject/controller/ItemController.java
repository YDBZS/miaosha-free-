package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.error.BussinessException;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 商品接口
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/26 14:30
 */
@Api(value = "商品接口", tags = {"商品接口"})
@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Resource
    private ItemService itemService;


    @ApiOperation(value = "创建商品", httpMethod = "POST")
    @PostMapping("/createItem")
    @ResponseBody
    public CommonReturnType createItem(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "description") String description,
        @RequestParam(name = "price") BigDecimal price,
        @RequestParam(name = "stock") Integer stock,
        @RequestParam(name = "imgUrl") String imgUrl
    ) throws BussinessException {
        //封装Service请求用来创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);

        ItemModel item = itemService.createItem(itemModel);

        ItemVO itemVO = this.converteVoFromModel(item);

        return CommonReturnType.create(itemVO);

    }


    private ItemVO converteVoFromModel(ItemModel itemModel){

        if (Objects.isNull(itemModel)){
            return null;
        }

        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        return itemVO;
    }

}
