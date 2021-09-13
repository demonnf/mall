package com.demon.mall.Service;

import com.demon.mall.Resdto.AddCategory;
import com.demon.mall.Resdto.UpdateCategory;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
     void AddCategory(AddCategory addCategory);

    void UpdateCategory(UpdateCategory updateCategory);

    void DeleteCategory(Integer id);

    PageInfo ListCategory(Integer pagenum, Integer pagesize);
}
