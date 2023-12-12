package com.quartz.exam;

import java.util.Timer;
import java.util.TimerTask;

public class TimerSchedulerExample {
    public static void main(String[] args) {
        Timer timer = new Timer(); //예약해야할 작업과 시간을 설정하는 클래스
        TimerTask task = new TimerTask() { //예약할 작업을 설정하는 클래스
            public void run() {
                System.out.println("Task executed after 5 seconds");
            }
        };
        timer.schedule(task, 5000);
    }
}
