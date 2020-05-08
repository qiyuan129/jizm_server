package com.example.jizm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.jizm.annotation.UserLoginToken;
import com.example.jizm.service.BillService;
import com.example.jizm.util.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags={"报表相关接口"},protocols = "http")
public class StatisticController {
    @Autowired
    BillService billService;

    @GetMapping("/web/statistic/byAccount")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户登录时获取的token",required = true,dataType="String",
                    paramType = "header"),
            @ApiImplicitParam(name="type",value="标记查询的类别，0为支出，1为收入",required = true,dataType = "Int",
                    paramType = "query")
    })
    @UserLoginToken
    public BaseResult<JSONArray> getStatisticByAccount(@RequestAttribute @ApiIgnore int userId, @RequestParam int type,
                                                       @RequestParam int year){
        JSONArray resultJson=billService.getYearStatisticByAccount(userId,type,year);
        return BaseResult.successWithData(resultJson);
    }

    @GetMapping("/web/statistic/byCategory")
    @UserLoginToken
    public BaseResult<JSONObject> getStatisticByCategory(@RequestAttribute @ApiIgnore int userId){
        JSONObject resultObject=billService.getStatisticByCategory(userId);
        return BaseResult.successWithData(resultObject);
    }

//    @GetMapping("/web/statistic/getJson")
//    public BaseResult<JSONArray> getJson(){
//        JSONArray array=new JSONArray();
//        JSONObject object=new JSONObject();
//        object.put("支付宝",255);
//        object.put("微信",3123);
//        array.add(object);
//
//        return BaseResult.successWithData(array);
//    }


}