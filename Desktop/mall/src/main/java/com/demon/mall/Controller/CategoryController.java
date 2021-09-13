package com.demon.mall.Controller;

import com.demon.mall.Common.ApiResponse;
import com.demon.mall.Common.Encryption;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Resdto.AddCategory;
import com.demon.mall.Resdto.UpdateCategory;
import com.demon.mall.Service.CategoryService;
import com.demon.mall.Service.UserService;
import com.demon.mall.model.pojo.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CategoryController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @ApiOperation("后台商品添加目录")
    @ResponseBody
    @PostMapping("admin/category/add")
    public ApiResponse AddCategory(@Valid @RequestBody AddCategory addCategory) {
        categoryService.AddCategory(addCategory);
        return ApiResponse.success();
    }
    @ApiOperation("后台商品更新目录")
    @ResponseBody
    @PostMapping("admin/category/update")
    public ApiResponse AddCategory( @Valid @RequestBody UpdateCategory updateCategory) {
        categoryService.UpdateCategory(updateCategory);
        return ApiResponse.success();
    }
    @ApiOperation("后台商品删除目录")
    @ResponseBody
    @PostMapping("admin/category/delete")
    public ApiResponse AddCategory(@RequestParam("id") Integer id) {
        categoryService.DeleteCategory(id);
        return ApiResponse.success();
    }

}
