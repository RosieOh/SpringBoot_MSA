package com.quartz.exam;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
//10초마다 "Hello Quartz!"를 출력하는 Job을 실행하는 예시
public class QuartzExample {
    public static void main(String[] args) throws SchedulerException {
        // Job 생성
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "group1")
                .build();

        // Trigger 생성
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();

        // 스케줄러 생성 및 Job, Trigger 등록
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}