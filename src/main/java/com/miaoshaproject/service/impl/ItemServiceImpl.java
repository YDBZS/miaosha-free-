package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.ItemDOMapper;
import com.miaoshaproject.dao.ItemStockDOMapper;
import com.miaoshaproject.dataobjects.ItemDO;
import com.miaoshaproject.dataobjects.ItemStockDO;
import com.miaoshaproject.error.BussinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * <p>TODO</p>
 *
 * @author 多宝
 * @since 2020/7/26 13:22
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ValidatorImpl validator;

    @Resource
    private ItemDOMapper itemDOMapper;

    @Resource
    private ItemStockDOMapper itemStockDOMapper;

    private ItemDO converteItemDofromItemModel(ItemModel itemModel){
        if (null == itemModel){
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());
        return itemDO;
    }

    private ItemStockDO converteItemStockDOFromItemModel(ItemModel itemModel){
        if (null == itemModel){
            return null;
        }

        ItemStockDO stockDO = new ItemStockDO();
        stockDO.setItemId(itemModel.getId());
        stockDO.setStaock(itemModel.getStock());
        return stockDO;

    }

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BussinessException {
        //校验入参
        ValidationResult result = this.validator.validator(itemModel);
        if (result.isHasError()){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        //转换itemModel -> DataObject
        ItemDO itemDO = this.converteItemDofromItemModel(itemModel);
        //写入数据库
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());
        ItemStockDO itemStockDO = this.converteItemStockDOFromItemModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);

        //返回创建完成的对象
        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        return null;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (Objects.isNull(itemDO)){
            return null;
        }
        //操作获得库存数量
        ItemStockDO stockDO = itemStockDOMapper.selectByItemId(itemDO.getId());

        //将DataObject转换成Model
        return this.converteModelFromDataObject(itemDO, stockDO);
    }

    private ItemModel converteModelFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
        itemModel.setStock(itemStockDO.getStaock());
        return itemModel;
    }
}

