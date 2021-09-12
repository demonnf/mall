package com.demon.mall.Service.impl;

import com.demon.mall.Exception.mallException;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Resdto.AddCategory;
import com.demon.mall.Service.CategoryService;
import com.demon.mall.model.dao.CategoryMapper;
import com.demon.mall.model.pojo.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatagoryServiceimpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public void AddCategory(AddCategory addCategory) {
        Category category = new Category();
        BeanUtils.copyProperties(addCategory, category);
        Category category1 = categoryMapper.SelectByname(addCategory.getName());
        if (category1 != null) {
            throw new mallException(mallExceptionEunm.CATAGORY_ISHAVE);
        }
        int i = categoryMapper.insertSelective(category);
        if(i==0){
            throw  new mallException(mallExceptionEunm.INSERTCATE_FAIL);
        }


    }
}
