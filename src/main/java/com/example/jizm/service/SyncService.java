package com.example.jizm.service;

import com.example.jizm.dao.AccountMapper;
import com.example.jizm.dao.BillMapper;
import com.example.jizm.dao.CategoryMapper;
import com.example.jizm.dao.PeriodicMapper;
import com.example.jizm.model.*;
import com.example.jizm.util.SyncRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void processUploadRecords(List<SyncRecords> tableList){
        SyncRecords<Account> accounts=tableList.get(0);
        SyncRecords<Bill> bills=tableList.get(1);
        SyncRecords<Category> categories=tableList.get(2);
        SyncRecords<Periodic> periodic=tableList.get(3);

        this.accountsSync(accounts);
        this.billSync(bills);
        this.categorySync(categories);
        this.periodicSync(periodic);
    }

    @Transactional
    public void accountsSync(SyncRecords<Account> accounts){

    }

    public void billSync(SyncRecords<Bill> bills){

    }

    public void categorySync(SyncRecords<Category> categories){

    }

    public void periodicSync(SyncRecords<Periodic> periodic){

    }
}
