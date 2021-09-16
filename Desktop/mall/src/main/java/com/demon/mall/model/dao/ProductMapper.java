package com.demon.mall.model.dao;

import com.demon.mall.model.pojo.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product SelectByname(String name);

    int Batchupdatesellstatus(Integer[] ids,Integer sellstatus);

    List<Product> selectlist();
}