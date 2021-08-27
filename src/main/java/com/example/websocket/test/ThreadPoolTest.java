package com.example.websocket.test;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author khzhang
 * @date 2021/6/24 18:03
 * @description 线程池测试
 */
public class ThreadPoolTest {
    public static void main1(String[] args) {
        ThreadFactory build = new ThreadFactoryBuilder().setNameFormat("RegionTread-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),build,
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 1000; i++) {
            System.out.println("次数:"+i);
            threadPoolExecutor.execute(() -> {
                System.out.println("线程池名" + Thread.currentThread().getName());
                System.out.println("池子数量:"+threadPoolExecutor.getPoolSize());
            });
        }

    }

    public static void main2(String[] args) throws InterruptedException {
        ThreadFactory build = new ThreadFactoryBuilder().setNameFormat("RegionTread-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),build,
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 1000; i++) {
            System.out.println("次数:"+i);
            if (i == 120){
                threadPoolExecutor.awaitTermination(2500,TimeUnit.MILLISECONDS);
                return;
            }
            Integer a = i;
            threadPoolExecutor.execute(() -> {
                System.out.println("线程池名" + Thread.currentThread().getName() + "次数:"+ a);

            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (threadPoolExecutor.getActiveCount() != 0){
            Thread.sleep(1000);
            System.out.println(String.format("此时有(%s)条活跃线程",threadPoolExecutor.getActiveCount()));
            System.out.println(String.format("此时已完成(%s)条数据",threadPoolExecutor.getCompletedTaskCount()));
        }
        System.out.println("----------------------线程执行完毕");
    }


    public static void main(String[] args) {

    }

}
