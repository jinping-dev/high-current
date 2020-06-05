package com.tinner.demo.service;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * @Classname UserService
 * @Description UserService
 * @Date 2020/6/5 4:08 下午
 * @Created by tinner
 */
public interface UserThreadService {
    JSONObject getUserInfo();
}
