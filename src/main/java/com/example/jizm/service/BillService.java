package com.example.jizm.service;

import com.example.jizm.dao.BillMapper;
import com.example.jizm.model.Account;
import com.example.jizm.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillService {
    @Autowired
    BillMapper billMapper;

    public List<Bill> getBillList(int userId,int categoryId){
        List<Bill> billList=null;

        // categoryId为0时，返回所有类别的账单列表
        // categoryId大于0时，返回指定类别的账单列表
        // categoryId小于0时，-1返回收入账单列表，-2返回支出账单列表
        if(categoryId == 0){
            billList=billMapper.selectAllByUserId(userId);
        }
        else if(categoryId>0){
            billList=billMapper.selectAllByCategoryIdAndUserId(categoryId,userId);
        }
        else{
            if(categoryId==-1){
                billList=billMapper.selectAllByUserIdAndType(userId,1);
            }
            else if(categoryId==-2){
                billList=billMapper.selectAllByUserIdAndType(userId,0);
            }
            else{
                //错误处理
            }
        }
        return billList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertBillListFromWeb(List<Bill> bills,int userId){
        int localIdCount=billMapper.selectMaxLocalIdByUserId(userId);
        for(Bill bill:bills){
            localIdCount++;
            bill.setUserId(userId);
            bill.setLocalId(localIdCount);
            //这一条正确的前提是每个用户都有一个保留的accountId为1的支付宝账户
            bill.setAccount(new Account(1));
            billMapper.insertSelective(bill);
        }
    }

}
