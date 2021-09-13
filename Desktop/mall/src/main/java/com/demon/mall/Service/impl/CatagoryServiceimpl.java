package com.demon.mall.Service.impl;

import com.demon.mall.Exception.mallException;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Resdto.AddCategory;
import com.demon.mall.Resdto.UpdateCategory;
import com.demon.mall.Service.CategoryService;
import com.demon.mall.model.dao.CategoryMapper;
import com.demon.mall.model.Vo.CategoryVO;
import com.demon.mall.model.pojo.Category;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatagoryServiceimpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void AddCategory(AddCategory addCategory) {
        CategoryVO category = new CategoryVO();
        BeanUtils.copyProperties(addCategory, category);
        CategoryVO category1 = categoryMapper.SelectByname(addCategory.getName());
        if (category1 != null) {
            throw new mallException(mallExceptionEunm.CATAGORY_ISHAVE);
        }
        int i = categoryMapper.insertSelective(category);
        if (i == 0) {
            throw new mallException(mallExceptionEunm.INSERTCATE_FAIL);
        }


    }

    @Override
    public void UpdateCategory(UpdateCategory updateCategory) throws mallException {
        CategoryVO category = new CategoryVO();
        BeanUtils.copyProperties(updateCategory, category);
        if (category.getName() != null) {
            CategoryVO category1 = categoryMapper.SelectByname(category.getName());
            if (category1 != null && !category1.getId().equals(updateCategory.getId())) {
                throw new mallException(mallExceptionEunm.CATEGORY_ISNOTFOUND);
            }
        }
        int i = categoryMapper.updateByPrimaryKey(category);
        if (i == 0) {
            throw new mallException(mallExceptionEunm.UPDATE_FAIL);
        }

    }

    @Override
    public void DeleteCategory(Integer id) {
        CategoryVO category = categoryMapper.selectByPrimaryKey(id);
        if (category == null) {
            throw new mallException(mallExceptionEunm.ID_ISNULL);
        }
        int i = categoryMapper.deleteByPrimaryKey(id);
        if (i == 0) {
            throw new mallException(mallExceptionEunm.DELETE_FAIL);
        }
    }

    @Override
    public PageInfo ListCategory(Integer pagenum, Integer pagesize) {
       PageHelper.startPage(pagenum,pagesize,"type ,order_num");
       List<Category> list=categoryMapper.selectCategoryList();
        PageInfo pageInfo=new PageInfo<>(list);
        return pageInfo;


    }
}
