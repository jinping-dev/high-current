package com.tinner.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tinner.demo.custom.TinnerFutureTask;
import com.tinner.demo.service.RemoteService;
import com.tinner.demo.service.UserService;
import com.tinner.demo.service.UserThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @Classname UserServiceImpl
 * @Description 接口串行，必须要等待返回结果才能执行下面的代码
 * @Date 2020/6/5 4:08 下午
 * @Created by tinner
 */
@Service
public class UserThreadServiceImpl implements UserThreadService {

    @Autowired
    private RemoteService remoteService;

    static ExecutorService threads = Executors.newFixedThreadPool(10);


    @Override
    public JSONObject getUserInfo() {
        long lowTime = System.currentTimeMillis();

        Callable<JSONObject> queryUserInfo = () -> {
            String gradeInfo = remoteService.getGradeInfo();
            return JSONObject.parseObject(gradeInfo);

        };

        Callable<JSONObject> queryUserProvince = () -> {
            String subjectInfo = remoteService.getSubjectInfo();
            return JSONObject.parseObject(subjectInfo);

        };


        TinnerFutureTask<JSONObject> gradeTask = new TinnerFutureTask<>(queryUserInfo);
        TinnerFutureTask<JSONObject> subjectTask = new TinnerFutureTask<>(queryUserProvince);


//        new Thread(gradeTask).start();
//        new Thread(subjectTask).start();

        threads.submit(gradeTask);
        threads.submit(subjectTask);

        JSONObject result = new JSONObject();

        //阻塞  task是需要等返回值的
        try {
            result.putAll(gradeTask.get());
            result.putAll(subjectTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("总耗费的时间为:"+ (System.currentTimeMillis() - lowTime));
        return result;
    }
}
