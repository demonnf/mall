package com.demon.mall.Service.impl;

import com.demon.mall.Exception.mallException;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Resdto.AddCategory;
import com.demon.mall.Resdto.UpdateCategory;
import com.demon.mall.Service.CategoryService;
import com.demon.mall.model.dao.CategoryMapper;
import com.demon.mall.model.pojo.Category;
import org.aspectj.weaver.ast.Var;
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
        if (i == 0) {
            throw new mallException(mallExceptionEunm.INSERTCATE_FAIL);
        }


    }

    @Override
    public void UpdateCategory(UpdateCategory updateCategory) throws mallException{
        Category category = new Category();
        BeanUtils.copyProperties(updateCategory, category);
        if (category.getName() != null) {
            Category category1 = categoryMapper.SelectByname(category.getName());
            if (category1 !=null &&! category1.getId().equals(updateCategory.getId())){
                throw new mallException(mallExceptionEunm.CATEGORY_ISNOTFOUND);
            }
        }
        int i = categoryMapper.updateByPrimaryKey(category);
        if (i==0){
            throw new mallException(mallExceptionEunm.UPDATE_FAIL);
        }

    }

    @Override
    public void DeleteCategory(Integer id) {
        int i = categoryMapper.deleteByPrimaryKey(id);
        if(i==0){
            throw new mallException(mallExceptionEunm.DELETE_FAIL);
        }
    }
}
