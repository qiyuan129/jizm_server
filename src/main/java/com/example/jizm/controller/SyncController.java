package com.example.jizm.controller;

import com.example.jizm.model.Account;
import com.example.jizm.model.Bill;
import com.example.jizm.util.SyncRecords;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value="处理app端同步请求的控制器",protocols = "http")
public class SyncController {

    @ApiOperation(value="app发送同步记录",notes="app发送需要同步的记录，服务器接收后进行批量处理",protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "list", value = "同步记录列表，注意：默认排序：account表-》Bill表-》Category表-》Periodic表",
                    dataType = "List<SyncRecords>", paramType = "body"),
            @ApiImplicitParam(name = "token", value = "用户端保存的令牌", dataType = "String", paramType = "header")
    })
    @PostMapping("/app/synchronization")
    public SyncRecords<Account> readJsonTest(@RequestHeader String token,@RequestBody List<SyncRecords> list){
//        List<SyncRecords> list=JSONObject.parseArray(json,SyncRecords.class)

//        Type type = new TypeReference<List<SyncRecords>>() {}.getType();
//        List<SyncRecords> list= JSON.parseObject(json,type);
//        SyncRecords<Account> accounts=list.get(0);
//        return accounts;

        SyncRecords<Account> accounts=list.get(0);
        return accounts;
    }

//    @RequestMapping("app/test2")
//    public List<SyncRecords> createJsonArray(){
//        ArrayList<SyncRecords> records=new ArrayList<>();
//
//        SyncRecords<Account> accounts=new SyncRecords<>();
//        accounts.setNeedSync(true);
//        SyncRecords<Bill> bills=new SyncRecords<>();
//        bills.setNeedSync(true);
//
//        records.add(accounts);
//        records.add(bills);
//        return records;
//    }
}
