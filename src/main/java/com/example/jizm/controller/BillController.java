package com.example.jizm.controller;

import com.example.jizm.config.BaseResult;
import com.example.jizm.dao.BillMapper;
import com.example.jizm.model.Bill;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(tags={"账单相关接口"},protocols = "http")
@RestController
public class BillController {
    @Resource
    BillMapper billMapper;

    @GetMapping("/web/bills")
    @ApiOperation(value="获取账单列表",notes="根据参数获取指定类别的帐单列表",protocols = "http")
    @ApiImplicitParams({
//            @ApiImplicitParam(name="userId",value="用户id",required = true,dataType = "int",
//                    paramType = "query"),
            @ApiImplicitParam(name="token",value="用户登录时获得的token",required = true,dataTypeClass = String.class,
                    paramType = "header"),
            @ApiImplicitParam(name="categoryId",value="账单类别,该参数值为空或者为0时将不分类别返回指定用户的所有账单",example = "1",
                    dataType = "Int", paramType = "query")
    })
    public BaseResult<List> getBills(@RequestHeader String token,
                                     @RequestParam int categoryId){
        //@TODO  有待修改（加入对categoryId为空的处理）
        List<Bill> billList=new ArrayList<>();
        return BaseResult.successWithData(billList);
    }

    @GetMapping("/web/bill")
    @ApiOperation(value="获取一条账单的信息",notes="根据token中的用户id和参数中的local_id查询一条账单的信息",protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户登录时获得的token",required = true,dataTypeClass = String.class,
                    paramType="header"),
            @ApiImplicitParam(name="local_id",value="账单的local_id",required = true,dataType = "Int",example = "1",
                    paramType = "query")
    })
    public BaseResult<Bill> getBill(@RequestHeader String token, @RequestParam int local_id){
        Bill bill=new Bill();
        return BaseResult.successWithData(bill);
    }

}
