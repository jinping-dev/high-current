package com.tinner.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tinner.demo.service.RemoteService;
import com.tinner.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname UserServiceImpl
 * @Description 接口串行，必须要等待返回结果才能执行下面的代码
 * @Date 2020/6/5 4:08 下午
 * @Created by tinner
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RemoteService remoteService;


    @Override
    public JSONObject getUserInfo() {
        long lowTime = System.currentTimeMillis();
        String gradeInfo = remoteService.getGradeInfo();
        JSONObject grade = JSONObject.parseObject(gradeInfo);

        String subjectInfo = remoteService.getSubjectInfo();
        JSONObject subject = JSONObject.parseObject(subjectInfo);

        JSONObject result = new JSONObject();
        result.putAll(grade);
        result.putAll(subject);
        System.out.println("总耗费的时间为:"+ (System.currentTimeMillis() - lowTime));
        return result;
    }
}
