package com.demon.mall.Service;

import com.demon.mall.Resdto.AddProduct;
import com.demon.mall.Resdto.UpdateProduct;
import com.demon.mall.model.pojo.Product;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
  void  Addproduct(AddProduct addProduct);
  void  Updateproduct(UpdateProduct updateProduct);

  void Deleteproduct(Integer id);

  void BatchupdateSellstatus(Integer[] ids, Integer sellstatus);

  PageInfo productlist(Integer pagenum, Integer pagesize);

  Product detail(Integer id);
}
