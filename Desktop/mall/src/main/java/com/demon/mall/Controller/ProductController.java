package com.demon.mall.Controller;

import com.demon.mall.Common.ApiResponse;
import com.demon.mall.Common.Encryption;
import com.demon.mall.Exception.mallException;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Resdto.AddProduct;
import com.demon.mall.Resdto.UpdateProduct;
import com.demon.mall.Service.ProductService;
import com.demon.mall.model.pojo.Product;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @ApiOperation("产品添加")
    @ResponseBody
    @PostMapping("/admin/product/add")
    public ApiResponse add(@Valid @RequestBody AddProduct addProduct) {
        productService.Addproduct(addProduct);
        return ApiResponse.success();
    }

    @ApiOperation("上传图片操作")
    @PostMapping("/admin/upload/file")
    @ResponseBody
    public ApiResponse UploadImagefile(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) {
        //获得原始名字
        String fileName = file.getOriginalFilename();
        //获得文件后缀
        String substring = fileName.substring(fileName.lastIndexOf("."));
        //生成uuid
        UUID uuid = UUID.randomUUID();
        //创建新文件名
        String newfilename = uuid.toString() + substring;
        //创建文件夹
        File filedir = new File(Encryption.Uploadfile);
        //创建文件夹中文件名
        File filedsk = new File(Encryption.Uploadfile + newfilename);
        //文件夹不存在则创建文件夹
        if (!filedir.exists()) {
            if (!filedir.mkdir()) {
                throw new mallException(mallExceptionEunm.MKDIR_FAIL);
            }
        }
        try {
            file.transferTo(filedsk);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return ApiResponse.success(getHost(new URI(httpServletRequest.getRequestURI() + "")) + "/image/" + newfilename);
        } catch (URISyntaxException e) {
            return ApiResponse.error(mallExceptionEunm.UPDATE_FAIL);
        }
    }

    private URI getHost(URI uri) {
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(),
                    null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }
    @ResponseBody
    @PostMapping("admin/product/update")
    @ApiOperation("产品更新")
    public ApiResponse UpdateProduct(@RequestBody UpdateProduct updateProduct){
        productService.Updateproduct(updateProduct);
        return ApiResponse.success();
    }
    @ResponseBody
    @PostMapping("admin/product/delete")
    @ApiOperation("产品删除")
    public ApiResponse DeleteProduct(@RequestParam("id") Integer id){
        productService.Deleteproduct(id);
        return ApiResponse.success();
    }
    @ResponseBody
    @PostMapping("admin/product/batchupdate")
    @ApiOperation("产品批量上下架")
    public ApiResponse BatchupdateSellstatus(@RequestParam("ids")  Integer[] ids,@RequestParam("Sellstatus") Integer sellstatus){
        productService.BatchupdateSellstatus(ids,sellstatus);
        return ApiResponse.success();
    }
    @ResponseBody
    @PostMapping("admin/product/list")
    @ApiOperation("前台产品列表")
    public ApiResponse Productlist(@RequestParam("pagenum")  Integer pagenum,@RequestParam("pagesize") Integer pagesize){
        PageInfo productlist = productService.productlist(pagenum, pagesize);
        return ApiResponse.success(productlist);
    }
    @ResponseBody
    @PostMapping("admin/product/detail")
    @ApiOperation("前台单个产品详情")
    public ApiResponse Productdetail(@RequestParam("id") Integer id ){
        Product product = productService.detail(id);
        return ApiResponse.success(product);
    }
}
