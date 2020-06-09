package com.example.jizm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.jizm.dao.AccountMapper;
import com.example.jizm.dao.BillMapper;
import com.example.jizm.dao.CategoryMapper;
import com.example.jizm.model.Account;
import com.example.jizm.model.Bill;
import com.example.jizm.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BillService {
    @Autowired
    BillMapper billMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
    public JSONArray getYearStatisticByAccount(int userId,int type,int specifiedYear){
        List<Bill> billList=billMapper.selectAllByUserIdAndType(userId,type);
        List<Account> accountList=accountMapper.selectAllByUserId(userId);

        JSONArray resultJsonArray=new JSONArray();
        for(int i=0;i<12;i++){
            JSONObject object=new JSONObject();
            //初始化每个月中的类别总额
            for(Account account:accountList){
                object.put(account.getName(),0);
            }
            resultJsonArray.add(object);
        }

        for(Bill bill:billList){
            //获取账单所在月份（取值范围0~11）
            Date billDate=bill.getDate();
            Calendar billCalendar=Calendar.getInstance();
            billCalendar.setTime(billDate);
            int month=billCalendar.get(Calendar.MONTH);
            int year=billCalendar.get(Calendar.YEAR);

            //目前只统计在2020年的账单，
            if(year==specifiedYear) {
                //获取账单所对应的账户名
                JSONObject monthObject = resultJsonArray.getJSONObject(month);
                String accountName = bill.getAccount().getName();

                //更新总金额
                int original = monthObject.getInteger(accountName);
                monthObject.put(accountName, original + bill.getMoney());
            }
        }


        return resultJsonArray;
    }

    @Transactional(rollbackFor = Exception.class)
    public JSONObject getStatisticByCategory(int userId){
        JSONObject resultObject=new JSONObject();
        //查询支出的类别有哪些（type为0为支出）
        List<Category> categoryList=categoryMapper.selectByUserIdAndType(userId,0);

        for(Category category:categoryList){
            JSONObject categoryObject=new JSONObject();
            JSONArray top3Array=new JSONArray();
            //查询该类别下的账单
            List<Bill> billList=billMapper.selectAllByCategoryIdAndUserIdOrderByMoneyDesc(category,userId);
            Double sum=0.0;

            //统计总额、添加top3
            for(int i=0;i<billList.size();i++){
                Bill bill=billList.get(i);
                //把前三个加入top3数组中
                if(i<3){
                    String jsonStr=JSON.toJSONString(bill);
                    JSONObject billObject=JSONObject.parseObject(jsonStr);
                    top3Array.add(billObject);
                }
                //统计总额
                sum+=bill.getMoney();
            }

            categoryObject.put("total",sum);
            categoryObject.put("top3",top3Array);
            resultObject.put(category.getName(),categoryObject);
        }
        return resultObject;
    }

    @Transactional(rollbackFor = Exception.class)
    public JSONObject getAllBillsByMonthAndAccount(int userId,int specifiedYear,int accountId){
        JSONObject resultObject=new JSONObject();
        JSONArray incomeArray=new JSONArray();
        JSONArray outcomeArray=new JSONArray();
        List<Bill> incomeBillList=billMapper.selectAllByUserIdAndTypeAndAccount(userId,1,accountId);
        List<Bill> outcomeBillList=billMapper.selectAllByUserIdAndTypeAndAccount(userId,0,accountId);

        //初始化收入账单数组
        for(int i=0;i<12;i++){
            JSONArray monthJSONArray=new JSONArray();
            incomeArray.add(monthJSONArray);
        }

        for(Bill bill:incomeBillList){
            //获取账单所在月份（取值范围0~11）
            Date billDate=bill.getDate();
            Calendar billCalendar=Calendar.getInstance();
            billCalendar.setTime(billDate);
            int month=billCalendar.get(Calendar.MONTH);
            int year=billCalendar.get(Calendar.YEAR);

            if(year==specifiedYear){
                JSONArray monthArray=incomeArray.getJSONArray(month);
                monthArray.add(bill);
            }
        }

        //初始化支出账单数组
        for(int i=0;i<12;i++){
            JSONArray monthJSONArray=new JSONArray();
            outcomeArray.add(monthJSONArray);
        }

        for(Bill bill:outcomeBillList){
            //获取账单所在月份（取值范围0~11）
            Date billDate=bill.getDate();
            Calendar billCalendar=Calendar.getInstance();
            billCalendar.setTime(billDate);
            int month=billCalendar.get(Calendar.MONTH);
            int year=billCalendar.get(Calendar.YEAR);

            if(year==specifiedYear){
                JSONArray monthArray=outcomeArray.getJSONArray(month);
                monthArray.add(bill);
            }
        }

        resultObject.put("income",incomeArray);
        resultObject.put("outcome",outcomeArray);

        return resultObject;
    }
}


