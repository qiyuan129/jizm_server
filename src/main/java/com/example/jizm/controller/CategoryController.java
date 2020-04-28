package com.example.jizm.controller;

import com.example.jizm.dao.CategoryMapper;
import com.example.jizm.model.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags={"账单类别相关接口"},protocols = "http")
public class CategoryController {
    @Resource
    CategoryMapper categoryMapper;

    @GetMapping("/web/categories")
    @ApiOperation(value="获取账单类别列表",notes="获取当前登录用户的账单类别列表")
    @ApiImplicitParam(name="token",value="用户登录时获取的token",required = true,dataType="String",
        paramType = "header")
    public List<Category> getCategoryList(@RequestHeader String token){
        //@TODO
        List<Category> categoryList=new ArrayList<>();
        return categoryList;
    }
}
