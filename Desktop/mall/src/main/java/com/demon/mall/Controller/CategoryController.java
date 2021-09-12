package com.demon.mall.Controller;

import com.demon.mall.Common.ApiResponse;
import com.demon.mall.Common.Encryption;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Resdto.AddCategory;
import com.demon.mall.Service.CategoryService;
import com.demon.mall.Service.UserService;
import com.demon.mall.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CategoryController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @ResponseBody
    @PostMapping("admin/category/add")
    public ApiResponse AddCategory(HttpSession session, @Valid @RequestBody AddCategory addCategory) {
        User CurrentUser = (User) session.getAttribute(Encryption.User_login);
        if (CurrentUser == null) {
            return ApiResponse.error(mallExceptionEunm.NEED_LOGIN);
        }
        boolean b = userService.CheckAdminRole(CurrentUser);
        if (b) {
            categoryService.AddCategory(addCategory);
            return ApiResponse.success();
        }
        return ApiResponse.error(mallExceptionEunm.NEED_ADMIN);
    }

}
