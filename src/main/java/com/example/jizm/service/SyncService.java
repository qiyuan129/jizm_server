package com.example.jizm.service;

import com.example.jizm.dao.AccountMapper;
import com.example.jizm.dao.BillMapper;
import com.example.jizm.dao.CategoryMapper;
import com.example.jizm.dao.PeriodicMapper;
import com.example.jizm.model.*;
import com.example.jizm.util.JsonUtil;
import com.example.jizm.util.SyncRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class SyncService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    PeriodicMapper periodicMapper;


    @Transactional(rollbackFor = Exception.class)
    public HashMap<String,SyncRecords> processUploadRecords(HashMap<String,SyncRecords> map){
        SyncRecords<Account> accountSyncRecords=map.get("Account");
        JsonUtil.recordListConvertion(accountSyncRecords.getRecordList(),Account.class);
        accountsSync(accountSyncRecords);

        SyncRecords<Bill> billSyncRecords=map.get("Bill");
        JsonUtil.recordListConvertion(billSyncRecords.getRecordList(),Bill.class);
        billSync(billSyncRecords);

        SyncRecords<Category> categorySyncRecords=map.get("Category");
        JsonUtil.recordListConvertion(categorySyncRecords.getRecordList(),Category.class);
        categorySync(categorySyncRecords);

        SyncRecords<Periodic> periodicSyncRecords=map.get("Periodic");
        JsonUtil.recordListConvertion(periodicSyncRecords.getRecordList(),Periodic.class);
        periodicSync(periodicSyncRecords);
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    public void accountsSync(SyncRecords<Account> accountSyncRecords){
        if(accountSyncRecords.isNeedSync()==true){
            List<Account> accounts=accountSyncRecords.getRecordList();

            //先根据state属性更新数据库
            for(Account account:accounts){
                //疑问：更新account会更新map中的原对象吗
                int state=account.getState();
                //新增记录
                if(state == 0){
                    //插入后，mybatis应该会自动把生成的key值回填到model类中，由此执行查询获得更新后的信息
                    accountMapper.insertSelective(account);
                }
                //修改记录
                else if(state==1){
                    //这里调用的update方法只会更新非空值，而从app传来的数据中，正好id、modified字段都是空的，所以只会更新需要更新的值
                    accountMapper.updateByUserIdAndLocalId(account,account.getUserId(),account.getLocalId());
                }
                //删除之后再写
                else{
                }
            }
            //更新后再获取最新的数据，修改引用，之后返回给客户端
            for(int i=0;i<accounts.size();i++){
                Account account=accounts.get(i);
                Account newAccount=accountMapper.selectByLocalIdAndUserId(account.getLocalId(),account.getUserId());
                //System.out.println(newAccount.toString());
                accounts.set(i,newAccount);
            }
        }
    }

    public void billSync(SyncRecords<Bill> billSyncRecords){
        if(billSyncRecords.isNeedSync()==true){
            List<Bill> bills=billSyncRecords.getRecordList();

            //先根据state属性更新数据库
            for(Bill bill:bills){
                int state=bill.getState();
                //新增记录
                if(state == 0){
                    //插入后，mybatis应该会自动把生成的key值回填到model类中，由此执行查询获得更新后的信息
                    billMapper.insertSelective(bill);
                }
                //修改记录
                else if(state==1){
                    //这里调用的update方法只会更新非空值，而从app传来的数据中，正好id、modified字段都是空的，所以只会更新需要更新的值
                    billMapper.updateByLocalIdAndUserId(bill,bill.getLocalId(),bill.getUserId());
                }
                //删除之后再写
                else{
                }
            }
            //更新后再获取最新的数据，修改引用，之后返回给客户端
            for(int i=0;i<bills.size();i++){
                Bill bill=bills.get(i);
                Bill newBill=billMapper.selectByLocalIdAndUserId(bill.getLocalId(),bill.getUserId());
                bills.set(i,newBill);
            }
        }
    }

    public void categorySync(SyncRecords<Category> categorySyncRecords){
        if(categorySyncRecords.isNeedSync()==true){
            List<Category> categories=categorySyncRecords.getRecordList();
            for(Category category:categories){
                int state=category.getState();
                if(state==0){
                    categoryMapper.insertSelective(category);
                }
                else if(state==1){
                    categoryMapper.updateByLocalIdAndUserId(category,category.getLocalId(),category.getUserId());
                }
                else{
                }
            }
            for(int i=0;i<categories.size();i++){
                Category category=categories.get(i);
                Category newCategory=categoryMapper.selectByLocalIdAndUserId(category.getLocalId(),category.getUserId());
                categories.set(i,newCategory);
            }
        }

    }

    public void periodicSync(SyncRecords<Periodic> periodicSyncRecords){
        if(periodicSyncRecords.isNeedSync()==true){
            List<Periodic> periodics=periodicSyncRecords.getRecordList();
            for(Periodic periodic:periodics){
                int state=periodic.getState();
                if(state==0){
                    periodicMapper.insertSelective(periodic);
                }
                else if(state==1){
                    periodicMapper.updateByLocalIdAndUserId(periodic,periodic.getLocalId(),periodic.getUserId());
                }
                else{
                }
            }
            for(int i=0;i<periodics.size();i++){
                Periodic periodic=periodics.get(i);
                Periodic newPeriodic=periodicMapper.selectByLocalIdAndUserId(periodic.getLocalId(),periodic.getUserId());
                periodics.set(i,newPeriodic);
            }
        }
    }
}
