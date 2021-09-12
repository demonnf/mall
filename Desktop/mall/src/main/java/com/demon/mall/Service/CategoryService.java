package com.demon.mall.Service;

import com.demon.mall.Resdto.AddCategory;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
     void AddCategory(AddCategory addCategory);
}
