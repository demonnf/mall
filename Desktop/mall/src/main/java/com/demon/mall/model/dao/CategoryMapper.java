package com.demon.mall.model.dao;

import com.demon.mall.model.Vo.CategoryVO;
import com.demon.mall.model.pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CategoryVO record);

    int insertSelective(CategoryVO record);

    CategoryVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CategoryVO record);

    int updateByPrimaryKey(CategoryVO record);
    CategoryVO SelectByname(String name);

    List<Category> selectCategoryList();
}