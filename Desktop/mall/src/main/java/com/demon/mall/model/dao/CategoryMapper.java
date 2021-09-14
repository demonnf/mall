package com.demon.mall.model.dao;

import com.demon.mall.model.Vo.CategoryVO;
import com.demon.mall.model.pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    Category SelectByname(String name);

    List<Category> selectCategoryList();

    List<Category>SelectByParentId(Integer parentId);
}