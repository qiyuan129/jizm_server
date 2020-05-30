package com.example.jizm.controller;

import com.example.jizm.annotation.UserLoginToken;
import com.example.jizm.util.BaseResult;
import com.example.jizm.dao.AccountMapper;
import com.example.jizm.model.Bill;
import com.example.jizm.model.Category;
import com.example.jizm.service.SyncService;
import com.example.jizm.util.SyncRecords;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
@Api(tags={"同步相关接口"},protocols = "http")
public class SyncController {
    @Autowired
    SyncService syncService;
    @Autowired
    AccountMapper accountMapper;

    @ApiOperation(value="app上传待同步记录",notes="app发送需要同步的记录，服务器接收后进行批量处理",protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "map", value = "同步记录哈希表，key：数据库表名（首字母大写），value：经过包装的同步记录（SyncRecords类型）",
                    dataTypeClass = HashMap.class, paramType = "body"),
            @ApiImplicitParam(name = "token", value = "用户端保存的令牌", dataTypeClass = String.class,
                    paramType = "header")
    })
    @PostMapping("/app/synchronization")
    @UserLoginToken
    public BaseResult<HashMap<String,SyncRecords>> processUpload(@RequestBody HashMap<String,SyncRecords> map){
        //  这里用户登录状态验证可能有点小问题
        syncService.processUploadRecords(map);
        return BaseResult.successWithData(map);
    }

    @PostMapping("/app/download")
    @ApiOperation(value="app请求从服务器端获取待同步记录",notes="根据app端发送的参数返回各表需要同步的记录",protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户登录时获取的token",required = true,dataType = "String",
                paramType = "header"),
            @ApiImplicitParam(name="map",value="各表的最新修改时间",required = true,dataType="HashMap",paramType = "body")
    })
    @UserLoginToken
    public BaseResult<HashMap<String,SyncRecords>> processDownload(@RequestAttribute @ApiIgnore int userId, @RequestBody HashMap<String,Date> map){
        HashMap<String,SyncRecords> result=syncService.processDownloadRequest(map,userId);
        return BaseResult.successWithData(result);
    }


    @GetMapping("/app/getJson")
    public HashMap<String,SyncRecords> createJsonArray(){
        HashMap<String,SyncRecords> map=new HashMap();
//        SyncRecords<Account> accounts=new SyncRecords<>();
//        accounts.setRecordList(new ArrayList<Account>());
//
//        accounts.setNeedSync(true);
//        //模拟添加一条待在同步中添加的账户
//        Account account1=new Account(3,1,"建设银行",33.3,0);
//        //模拟添加一条待在同步中修改的账户
//        Account account2=new Account(2,1,"微信",23.3,1);
//        accounts.getRecordList().add(account1);
//        accounts.getRecordList().add(account2);

        //为创建账单测试json做准备
        ArrayList<Bill> billRecords=new ArrayList<>();
        Bill bill1=new Bill(1,1,1,1,1,"买一台iphone se",2899.0,1);
        Bill bill2=new Bill(2,1,2,1,1,"买c++primerplus",102.0,1);
        billRecords.add(bill1);
        billRecords.add(bill2);

        SyncRecords<Bill> bills=new SyncRecords<>();
        bills.setNeedSync(true);
        bills.setTableName("Bill");
        bills.setRecordList(billRecords);

        //为创建类别测试json做准备
        ArrayList<Category> categoryRecords=new ArrayList<>();
        Category category1=new Category(1,1,"手机1",1,1);
        categoryRecords.add(category1);

        SyncRecords<Category> categories=new SyncRecords<>();
        categories.setNeedSync(true);
        categories.setTableName("Category");
        categories.setRecordList(categoryRecords);
//        map.put("Account",accounts);
        map.put("Bill",bills);
        map.put("Category",categories);
        return map;
    }
}
