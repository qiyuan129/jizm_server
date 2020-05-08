package com.example.jizm.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.jizm.annotation.UserLoginToken;
import com.example.jizm.util.BaseResult;
import com.example.jizm.dao.BillMapper;
import com.example.jizm.model.Bill;
import com.example.jizm.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@Api(tags={"账单相关接口"},protocols = "http")
@RestController
public class BillController {
    @Resource
    BillMapper billMapper;

    @Autowired
    BillService billService;

    @GetMapping("/web/bills")
    @ApiOperation(value="获取账单列表",notes="根据参数获取指定类别的帐单列表",protocols = "http")
    @ApiImplicitParams({
//            @ApiImplicitParam(name="userId",value="用户id",required = true,dataType = "int",
//                    paramType = "query"),
            @ApiImplicitParam(name="token",value="用户登录时获得的token",required = true,dataTypeClass = String.class,
                    paramType = "header"),
            @ApiImplicitParam(name="categoryId",value="账单类别id,不填写该参数值时将不分类别返回指定用户的所有账单；填-1返回当前登录" +
                    "用户的收入账单列表，填-2返回当前登录用户的支出账单列表", example = "1", dataType = "Int", paramType = "query")
    })
    @UserLoginToken
    public BaseResult<List<Bill>> getBills(@RequestAttribute @ApiIgnore int userId,
                                     @RequestParam int categoryId){
        List<Bill> billList=billService.getBillList(userId,categoryId);
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
    @UserLoginToken
    public BaseResult<Bill> getBill(@RequestAttribute @ApiIgnore int userId, @RequestParam int local_id){
        Bill bill=billMapper.selectByLocalIdAndUserId(local_id,userId);
        return BaseResult.successWithData(bill);
    }

    @PostMapping("/web/bills")
    @ApiOperation(value="批量上传账单",notes="将web端读取账单文件、处理后的账单批量插入到当前登录用户的账单表中",protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户登录时获取的token",required =true,dataType = "String",
                    paramType = "header"),
            @ApiImplicitParam(name="bills",value="待上传的帐单列表",required = true,dataTypeClass =List.class,
                    paramType = "body")
    })
    public BaseResult<String> uploadBills(@RequestAttribute @ApiIgnore int userId,@RequestBody List<Bill> bills){
        billService.insertBillListFromWeb(bills,userId);
        return BaseResult.successWithData("批量上传账单成功！");
    }

}
