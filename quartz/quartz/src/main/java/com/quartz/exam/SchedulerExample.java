package com.quartz.exam;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerExample {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // 5초 후에 실행되는 작업
        executor.schedule(() -> System.out.println("Hello World!"), 5, TimeUnit.SECONDS);

        // 1초 후부터 3초마다 실행되는 작업
        executor.scheduleAtFixedRate(() -> System.out.println("Fixed Rate"), 1, 3, TimeUnit.SECONDS);

        // 이전 작업이 완료된 후 2초 후에 실행되는 작업
        executor.scheduleWithFixedDelay(() -> System.out.println("Fixed Delay"), 0, 2, TimeUnit.SECONDS);
    }
}
