package com.example.jizm.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class JsonUtil {
    public static <T> List<T> recordListConvertion(List<T> list,Class<T> cls){
        for(int i=0;i<list.size();i++){
            String jsonStr=JSON.toJSONString(list.get(i));
            T t=JSON.parseObject(jsonStr,cls);
            list.set(i,t);
        }
        return list;
    }
}
