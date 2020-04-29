package com.example.jizm.service;

import com.example.jizm.dao.BillMapper;
import com.example.jizm.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                billList=billMapper.selectAllByUserIdAndType(userId,0);
            }
            else if(categoryId==-2){
                billList=billMapper.selectAllByUserIdAndType(userId,1);
            }
            else{
                //错误处理
            }
        }
        return billList;
    }
}
