package com.example.jizm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BillMapperTest {
    @Resource
    BillMapper billMapper;

    @Test
    public void selectAllByCategoryIdAndUserId() {

    }

    @Test
    public void selectByLocalIdAndUserId() {
    }
}