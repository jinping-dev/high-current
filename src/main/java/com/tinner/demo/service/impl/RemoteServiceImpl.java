package com.tinner.demo.service.impl;

import com.tinner.demo.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname RemoteServiceImpl
 * @Description RemoteServiceImpl
 * @Date 2020/6/5 4:18 下午
 * @Created by tinner
 */
@Service
public class RemoteServiceImpl implements RemoteService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String getGradeInfo(){
        long lowTime = System.currentTimeMillis();
        String result =  restTemplate.getForEntity("https://www.yooxue100.com/api/index/grade",String.class).getBody();
        System.out.println("获取年级耗费的时间为:"+ (System.currentTimeMillis() - lowTime));
        return result;
    }

    @Override
    public String getSubjectInfo(){
        long lowTime = System.currentTimeMillis();
        String result =  restTemplate.getForEntity("https://www.yooxue100.com/api/index/honor",String.class).getBody();
        System.out.println("获取省份耗费的时间为:"+( System.currentTimeMillis() - lowTime));
        return result;
    }
}
