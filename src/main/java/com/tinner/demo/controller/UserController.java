package com.tinner.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.tinner.demo.service.UserService;
import com.tinner.demo.service.UserThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @Classname UserController
 * @Description UserController
 * @Date 2020/6/5 3:39 下午
 * @Created by tinner
 */
@RestController
@ResponseBody
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserThreadService userThreadService;

    @RequestMapping("/info")
    public JSONObject getUserInfo(){
        return userThreadService.getUserInfo();
    }



    @RequestMapping("/infoByAsync")
    public Callable<JSONObject> getUserInfoAsync(){

        System.out.println("主线程开始。。。。。" + Thread.currentThread() + "======" + System.currentTimeMillis());
        Callable<JSONObject> callable = () -> {
            System.out.println("子线程开始。。。。。" + Thread.currentThread() + "======" + System.currentTimeMillis());
            JSONObject userInfo = userThreadService.getUserInfo();
            System.out.println("子线程结束。。。。。" + Thread.currentThread() + "======" + System.currentTimeMillis());
            return userInfo;
        };
        System.out.println("主线程结束。。。。。" + Thread.currentThread() + "======" + System.currentTimeMillis());
        return  callable;
    }
}
