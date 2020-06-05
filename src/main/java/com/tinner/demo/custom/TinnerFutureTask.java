package com.tinner.demo.custom;

import java.util.concurrent.*;

/**
 * @Classname TinnerFutureTask
 * @Description
 * 对于FutureTask来说，其实完成了两件事情：
 *  1、执行业务逻辑
 *  2、把请求方法阻塞，获取结果
 * @Date 2020/6/5 4:48 下午
 * @Created by tinner
 */
public class TinnerFutureTask<v> implements Runnable, Future<v> {

    //封装业务逻辑
    private Callable<v> callable;

    //用来接收结果
    v result = null;

    public TinnerFutureTask(Callable<v> callable){
        this.callable = callable;
    }
    //执行业务逻辑
    @Override
    public void run() {
        try {
            result = callable.call();

            synchronized (this){
                this.notifyAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //取结果
    @Override
    public v get() throws InterruptedException, ExecutionException {
        if (result != null){
            return result;
        }
        //等待
        synchronized (this){
            //底层用的park和unpark
            this.wait();
        }
        return result;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }



    @Override
    public v get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
