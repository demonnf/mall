package com.demon.mall.Service.impl;

import com.demon.mall.Exception.mallException;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Resdto.AddCategory;
import com.demon.mall.Resdto.UpdateCategory;
import com.demon.mall.Service.CategoryService;
import com.demon.mall.model.dao.CategoryMapper;
import com.demon.mall.model.Vo.CategoryVO;
import com.demon.mall.model.pojo.Category;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
    public void UpdateCategory(UpdateCategory updateCategory) throws mallException {
        Category category = new Category();
        BeanUtils.copyProperties(updateCategory, category);
        if (category.getName() != null) {
            Category category1 = categoryMapper.SelectByname(category.getName());
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
        Category category = categoryMapper.selectByPrimaryKey(id);
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
        PageHelper.startPage(pagenum, pagesize, "type ,order_num");
        List<Category> list = categoryMapper.selectCategoryList();
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;


    }
    @Cacheable(value = "listCategoryForCustomer")
    @Override
    public List<CategoryVO> listCategoryForCustomer(){
        ArrayList<CategoryVO> categoryVOList = new ArrayList<>();
        recursivelyFindCategories(categoryVOList,0);
        return categoryVOList;
    }
    private void recursivelyFindCategories(List<CategoryVO> categoryVOList,Integer parentId){
        //递归获取所有子类别，并组合成为一个目录树
        List<Category> categoryList = categoryMapper.SelectByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)) {
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                CategoryVO categoryVO = new CategoryVO();
                BeanUtils.copyProperties(category,categoryVO);
                categoryVOList.add(categoryVO);
                recursivelyFindCategories(categoryVO.getChildCategoryList(),categoryVO.getId());
            }
        }
    }
}
