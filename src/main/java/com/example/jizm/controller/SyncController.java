package com.example.jizm.controller;

import com.example.jizm.config.BaseResult;
import com.example.jizm.dao.AccountMapper;
import com.example.jizm.model.Account;
import com.example.jizm.model.Bill;
import com.example.jizm.service.SyncService;
import com.example.jizm.util.SyncRecords;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Api(value="处理app端同步请求的控制器",protocols = "http")
public class SyncController {
    @Autowired
    SyncService syncService;
    @Autowired
    AccountMapper accountMapper;

    @ApiOperation(value="app发送同步记录",notes="app发送需要同步的记录，服务器接收后进行批量处理",protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "list", value = "同步记录列表，注意：默认排序：account表-》Bill表-》Category表-》Periodic表",
                    dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = "token", value = "用户端保存的令牌", dataTypeClass = String.class,
                    paramType = "header")
    })
    @PostMapping("/app/synchronization")
    public BaseResult<HashMap> processUpload(@RequestHeader String token, @RequestBody HashMap<String,SyncRecords> map){
//        List<SyncRecords> list=JSONObject.parseArray(json,SyncRecords.class)

//        Type type = new TypeReference<List<SyncRecords>>() {}.getType();
//        List<SyncRecords> list= JSON.parseObject(json,type);
//        SyncRecords<Account> accounts=list.get(0);
//        return accounts;
        //@TODO
        syncService.processUploadRecords(map);
        //syncService.updateUploadResult(map.get("Account"));
        return BaseResult.successWithData(map);
    }

    @RequestMapping("app/getJson")
    public HashMap<String,SyncRecords> createJsonArray(){
        HashMap<String,SyncRecords> map=new HashMap();
        SyncRecords<Account> accounts=new SyncRecords<>();
        accounts.setRecordList(new ArrayList<Account>());

        accounts.setNeedSync(true);
        //模拟添加一条待在同步中添加的账户
        Account account1=new Account(3,1,"建设银行",33.3,0);
        //模拟添加一条待在同步中修改的账户
        Account account2=new Account(2,1,"微信",23.3,1);
        accounts.getRecordList().add(account1);
        accounts.getRecordList().add(account2);


//        SyncRecords<Bill> bills=new SyncRecords<>();
//        bills.setNeedSync(true);

        map.put("Account",accounts);
        //map.put("Bill",bills);
        return map;
    }
}
