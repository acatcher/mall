package com.yzx.mall.controller;

import com.github.pagehelper.PageInfo;
import com.yzx.mall.common.ApiResponse;
import com.yzx.mall.common.Constant;
import com.yzx.mall.exception.MallExceptionCode;
import com.yzx.mall.model.pojo.User;
import com.yzx.mall.model.request.AddCategoryReq;
import com.yzx.mall.model.request.UpdateCategoryReq;
import com.yzx.mall.model.vo.CategoryVO;
import com.yzx.mall.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    @Resource
    CategoryService categoryService;

    @ApiOperation("Add new Category")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiResponse addCategory(@Valid @RequestBody AddCategoryReq addCategoryReq,
                                   HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constant.USER);
        if(user == null){
            return ApiResponse.error(MallExceptionCode.HAVE_NOT_LOGIN);
        }

        if(!user.getRole().equals(2)){
            return ApiResponse.error(MallExceptionCode.NOT_ADMIN);
        }

        categoryService.addCategory(addCategoryReq);
        return ApiResponse.success();
    }


    @ApiOperation("Update Category")
    @PostMapping("/admin/category/update")
    @ResponseBody
    public ApiResponse updateCategory(@Valid @RequestBody UpdateCategoryReq updateCategoryReq,
                                      HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constant.USER);
        if(user == null){
            return ApiResponse.error(MallExceptionCode.HAVE_NOT_LOGIN);
        }

        if(!user.getRole().equals(2)){
            return ApiResponse.error(MallExceptionCode.NOT_ADMIN);
        }

        categoryService.updateCategory(updateCategoryReq);
        return ApiResponse.success();
    }

    @ApiOperation("Delete Category")
    @PostMapping("/admin/category/delete")
    @ResponseBody
    public ApiResponse deleteCategory(@RequestParam Integer id){

        categoryService.deleteCategory(id);
        return ApiResponse.success();

    }

    @ApiOperation("Admin List")
    @GetMapping("/admin/category/list")
    @ResponseBody
    public ApiResponse adminList(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize){

        PageInfo categoryListAdmin = categoryService.getCategoryListAdmin(pageNum, pageSize);
        return ApiResponse.success(categoryListAdmin);

    }


    @ApiOperation("User List")
    @GetMapping("/category/list")
    @ResponseBody
    public ApiResponse userList(){
        List<CategoryVO> list = categoryService.getListUser(0);

        return ApiResponse.success(list);

    }

}
