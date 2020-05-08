package com.example.jizm.controller;

import com.example.jizm.annotation.UserLoginToken;
import com.example.jizm.util.BaseResult;
import com.example.jizm.dao.CategoryMapper;
import com.example.jizm.model.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags={"账单类别相关接口"},protocols = "http")
@Validated
public class CategoryController {
    @Resource
    CategoryMapper categoryMapper;

    @GetMapping("/web/categories")
    @ApiOperation(value="获取账单类别列表",notes="获取当前登录用户的账单类别列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户登录时获取的token",required = true,dataType="String",
                    paramType = "header"),
            @ApiImplicitParam(name="categoryType",value="表示账单类别是收入类的还是支出类的整数,0为支出，1为收入",required = true,
                dataType = "Int",example = "0",paramType = "query")
    })
    @UserLoginToken
    public BaseResult<List<Category>> getCategoryList(@RequestAttribute @ApiIgnore int userId,
                                                      @RequestParam @Range(min=0,max=1) int categoryType){
        List<Category> categoryList=categoryMapper.selectByUserIdAndType(userId,categoryType);
        return BaseResult.successWithData(categoryList);
    }
}
