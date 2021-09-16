package com.demon.mall.Service.impl;

import com.demon.mall.Exception.mallException;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Resdto.AddProduct;
import com.demon.mall.Resdto.UpdateProduct;
import com.demon.mall.Service.ProductService;
import com.demon.mall.model.dao.ProductMapper;
import com.demon.mall.model.pojo.Product;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSeriviceimpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public void Addproduct(AddProduct addProduct) {
        //1.创建product实例
        Product product = new Product();
        //2.将新增的product复制到pojo中product类中，对数据进行一次覆盖
        BeanUtils.copyProperties(addProduct, product);
        //3.通过查询数据库是否有重名的商品，有就添加失败
        Product productOld = productMapper.SelectByname(addProduct.getName());
        if (productOld != null) {
            throw new mallException(mallExceptionEunm.CATAGORY_ISHAVE);
        }
        //4.进行插入数据操作并判断是否有效插入
        int count = productMapper.insertSelective(product);
        if (count == 0) {
            throw new mallException(mallExceptionEunm.INSERTCATE_FAIL);
        }
    }

    @Override
    public void Deleteproduct(Integer id) {
        if (id == null) {
            throw new mallException(mallExceptionEunm.CATEGORY_ISNOTFOUND);
        }
        Product product = productMapper.selectByPrimaryKey(id);
        if (product == null) {
            throw new mallException(mallExceptionEunm.CATEGORY_ISNOTFOUND);
        }

        int i = productMapper.deleteByPrimaryKey(id);
        if (i == 0) {
            throw new mallException(mallExceptionEunm.DELETE_FAIL);
        }
    }

    @Override
    public void Updateproduct(UpdateProduct updateProduct) {
        Product product = productMapper.SelectByname(updateProduct.getName());
        if (product != null && !product.getId().equals(updateProduct.getId())) {
            throw new mallException(mallExceptionEunm.CATEGORY_ISNOTFOUND);
        }
        Product currentProduct = new Product();
        BeanUtils.copyProperties(updateProduct, currentProduct);
        //必须使用Selective，这会选择性传入参数
        int i = productMapper.updateByPrimaryKeySelective(currentProduct);
        if (i == 0) {
            throw new mallException(mallExceptionEunm.INSERTCATE_FAIL);
        }

    }

    @Override
    public void BatchupdateSellstatus(Integer[] ids, Integer sellstatus) {
        int batchupdatesellstatus = productMapper.Batchupdatesellstatus(ids, sellstatus);
        if (batchupdatesellstatus == 0) {
            throw new mallException(mallExceptionEunm.BATCHUPDATE_FAIL);
        }
    }

    @Override
    public PageInfo productlist(Integer pagenum, Integer pagesize) {
        Page<Object> objects = PageHelper.startPage(pagenum, pagesize);
        List<Product> selectlist = productMapper.selectlist();
        PageInfo pageInfo = new PageInfo<>(selectlist);
        return pageInfo;
    }

    @Override
    public Product detail(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }
}