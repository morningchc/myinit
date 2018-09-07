package com.myexample.service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myexample.dao.FruitMapper;
import com.myexample.model.po.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chc on 2018/7/19.
 */
@Service
@Transactional
public class WelcomeService {

    @Autowired
    private FruitMapper fruitMapper;

    public ArrayList<Fruit> getAll(){
        return fruitMapper.getAll();
    }

    public ArrayList<Fruit> query(Fruit fruit,int pageIndex,int pageSize){
        PageHelper.startPage(pageIndex, pageSize);
        ArrayList<Fruit> list = fruitMapper.query(fruit);
        PageInfo<Fruit> page = new PageInfo<Fruit>(list);
        return list;
    }
}
