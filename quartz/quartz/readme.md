# 1. 스케쥴링(Scheduling) 과 배치 프로세싱(Batch Processing)

## 1-1. 스케줄링(Scheduling) 이란 ?

- 일정한 시간 간격으로 반복적인 작업을 수행하는 것을 의미함.
- 스케줄러를 이용하면 작업을 자동으로 수행하거나 주기적 혹은 일정 시간이 지난 후에 작업을 수행할 수 있도록하는 예약 작업과 작업 관리가 가능함.

## 1-2. 배치 프로세싱(Batch Processing) 이란?

- 특정 설정 클래스에 여러 일을 정해진 순서대로 처리할 수 있도록 일괄 프로그래밍하는 것을 의미함.
- 대량의 데이터나 복잡한 일련의 일들을 특정 이벤트 발생시에 자동 처리될 수 있도록 함.

## 1-3. 내부 클래스를 이용한 스케줄링

- 자바에서는 Timer 및 ScheduledExecutorService 클래스를 통하여 스케줄링을 제공함.

### 1-3-1. Timer 클래스를 이용한 스케줄링

- 특정한 시간 간격으로 작업을 수행할 수 있도록 예약
- Timer 클래스는 실제 동작하는 TimerTask 객체를 스케줄링하는 데 사용됨.
- TimerTask 객체는 Timer 클래스가 실행되는 쓰레드 안에서 수행됨.

| 메소드(Method)                           | 설명                                                                           |
|---------------------------------------|------------------------------------------------------------------------------|
| schedule(TimerTask task, long delay)  | 주어진 시간(delay) 이후에 작업(task)을 수행합니다.                                           |
| schedule(TimerTask task, long delay, long period) | 주어진 시간(delay) 이후에 작업(task)을 수행하고 이후 일정 시간(period) 간격으로 작업(task)을 반복해서 수행합니다. |
| schedule(TimerTask task, Date time) | 주어진 시간(time)에 작업(task)을 수행합니다. |
| schedule(TimerTask task, Date firstTime, long period) | 주어진 시간(firstTime)에 작업(task)을 수행하고, 이후 일정 시간(period) 간격으로 작업(task)을 반복해서 수행합니다. |

- 예시

```java
import java.util.Timer;
import java.util.TimerTask;
//작업의 내용은 run()에 기술하며, 예약 내용은 schedule()에 의해 설정함. 
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
```

<br><hr><br>

### 1-3-2. ScheduledExecutorService 인터페이스를 이용한 스케줄링

- java.util.concurrent 패키지에서 제공하며 Java에서 스케줄러를 사용하기 위한 기본적인 인터페이스임.
- ScheduledExecutorService를 사용하면 특정 시간에 작업을 실행하거나 주기적으로 작업을 실행하는 등의 스케줄링이 가능함.

| 메서드(Method) | 설명                                                                            |
|--------------------------|-------------------------------------------------------------------------------|
| schedule(Runnable command, long delay, TimeUnit unit) | 지정된 지연 시간 후에 한 번만 작업을 실행합니다. |
| scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) | 지정된 초기 지연 후에 첫 번째 작업을 실행하고, 이후에는 지정된 주기마다 작업을 실행합니다. |
| scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) | 지정된 초기 지연 후에 첫 번째 작업을 실행하고, 이후에는 이전 작업이 완료된 시점부터 지정된 지연 시간이 경과한 후에 작업을 실행합니다. |

- 예시

```java
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
```

<br><hr><br>

# 2. Spring Boot Quartz

- 자바 기반의 오픈 소스 작업 스케줄러 라이브러리로서 특정 시간에 작업을 실행하거나 특정 간격으로 작업을 수행함.
- 특히, 시스템의 자동화 및 효율성이 높아지며, 백그라운드 작업을 수행하는 서비스, 이메일 발송 예약, 데이터베이스 백업 등에 활용됨.

## 2-1. Spring Boot Quartz의 기능과 특징

### 2-1-1. Spring Boot Quartz의 기능

| 기능 | 기능설명 |
|----|------------------------------------------------------|
| 작업 스케줄링 | 작업 예약 및 주기 설정 기능 제공 |
| 작업 실행 및 관리 | 작업 실행, 중단, 재개, 삭제 기능 제공 |
| 작업 중단 및 재개 | 작업 중단, 재개 기능 제공 |
| 여러 작업 동시 실행 | 동시 여러 작업 실행 기능 제공 |
| 작업 실행 결과 처리 | 작업 실행 결과 처리 기능 제공 |

<br>

### 2-1-2. Spring Boot Quartz의 특징

| 기능                    | 기능설명                               |
|-----------------------|------------------------------------|
| 쉬운 작업 구현 | 스케줄링, 예약, 실행, 관리 등의 작업을 쉽게 구현 |
| 다양한 작업 예약 및 실행 | 다양한 작업을 예약하고 실행 |
| 작업 실행 결과 처리 및 기록 | 작업 실행 결과를 처리하고 이를 기록 |
| 다양한 트리거 지원 | 다양한 트리거(trigger)를 지원 |
| 무료 사용 | 오픈 소스이기 때문에 무료로 사용할 수 있음 |

<br><br>

## 2-2. Spring Boot Quartz 클래스 및 인터페이스

### 2-2-1. 클래스 및 인터페이스

| 클래스 또는 인터페이스 | 용도 설명                                      |
|-------------------|--------------------------------------------|
| Job | 실행할 작업에 대한 정보를 포함하는 인터페이스          |
| JobDetail | Job 클래스의 인스턴스와 Job 실행에 필요한 추가 정보를 포함하는 클래스 |
| Trigger | Job 실행을 스케줄링하기 위한 클래스                      |
| SimpleTrigger | 지정된 시간 간격으로 Job을 실행하기 위한 Trigger           |
| CronTrigger | Cron 표현식으로 Job을 스케줄링하기 위한 Trigger          |
| Scheduler | Job 실행과 Trigger 스케줄링을 관리하는 인터페이스           |
| SchedulerFactory | Scheduler 인스턴스를 생성하고 구성하기 위한 인터페이스         |

<br>

### 2-2-2. Job

- 실행할 작업을 정의하는 인터페이스
- Job 인터페이스를 상속(Implements) 하여 실행하고자 하는 작업을 정의하여 생명 주기에 따라 주기적으로 실행함.

<br>

### 2-2-3. Trigger

- 정의된 Job 이 실행되는 조건을 정의하는 인터페이스

| 트리거명 | 용도 설명 |
|-----------------|----------------------------------------------------|
| SimpleTrigger | 특정 시간 또는 주기적으로 한 번 실행되는 트리거 |
| CronTrigger | Cron 표현식을 사용하여 특정 시간에 실행되는 트리거 |
| CalendarIntervalTrigger | 지정된 간격으로 주기적으로 실행되는 트리거 |
| DailyTimeIntervalTrigger | 지정된 시간 범위 내에서 지정된 간격으로 주기적으로 실행되는 트리거 |

<br>

#### 2-2-3-1. SimpleTrigger

- 특정 시간에 한 번만 실행하거나 주기적으로 실행할 수 있도록 한 트리거

| 트리거 속성 | 설명 |
|----------------|-------------------------------------------------|
| repeatCount | 작업이 실행될 횟수를 지정합니다. 음이 아닌 정수 값이 될 수 있습니다. 0이 입력되면 작업이 무한히 실행됩니다. |
| repeatInterval | 작업이 실행되는 간격을 지정합니다. 밀리초 단위로 측정될 수 있는 어떤 정수 값이든 될 수 있습니다. |

```java
//Job이 시작 시간부터 10초 간격으로 5번 실행되며, repeatCount를 0으로 지정하면 무한히 실행됨
public class TriggerEx1 {
    public static void main(String[] args){
        SimpleTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(startTime)
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .withRepeatCount(5))
                .build();
    } 
}
```

<br>

#### 2-2-3-2. CronTrigger

- Cron 표현식을 사용하여 지정된 시간에 작업을 예약할 수 있음.

| 트리거 속성              | 설명                                                                                                                                    |
|---------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| cronExpression | Cron 표현식을 나타내는 문자열로 CronTrigger가 실행될 시간을 정의                                                                                           |
| timeZone | CronTrigger가 실행될 때 사용할 시간대를 나타내는 문자로<br> 설정하지 않으면 기본값으로 서버의 시간대를 사용                                                                   |
| misfireInstruction | CronTrigger가 실행되지 않은 경우 동작을 지정하는 데 사용되는 상수로<br> misfireInstruction을 MISFIRE_INSTRUCTION_FIRE_ONCE_NOW로 설정하면 CronTrigger가 다음 실행 시간에 실행 |
| priority | 트리거의 우선 순위를 나타내는 숫자입니다. 높은 우선 순위 값을 가진 트리거는 낮은 우선 순위 값을 가진 트리거보다 먼저 실행 |

```java
//매주 월요일 오전 10에 실행되는 CronTrigger
public class TriggerEx1 {
    public static void main(String[] args){
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 10 ? * MON"))
                .build();
    } 
}
```

<br>

##### Cron 표현식

- 리눅스 시스템에서 주기적인 작업을 자동으로 수행하기 위해 사용되는 문법
- cron 표현식의 구성은 분, 시, 일, 월, 요일 순서로 입력됨

```command
*    *    *    *    *
-    -    -    -    -
|    |    |    |    |
|    |    |    |    +----- 요일 (0 - 6) (0이나 7이 일요일)
|    |    |    +---------- 월 (1 - 12)
|    |    +--------------- 일 (1 - 31)
|    +-------------------- 시 (0 - 23)
+------------------------- 분 (0 - 59)
```

##### Cron 표현식 예시

| Cron 표현식 | 표현식 설명 |
|-----------------------|----------------------------------------|
| * * * * * | 매 분 |
| */30 * * * * | 30분마다 |
| 30 5 * * * | 매일 오전 5시 30분 |
| 30 5 * * 1 | 매주 월요일 오전 5시 30분 |
| 30 5 1 * * | 매월 1일 오전 5시 30분 |

<br>

### 2-2-4. Scheduler

- Job과 Trigger를 연결하여 실행시키는 역할을 하는 인터페이스


| 메서드 | 설명 |
|-----------------------------------------|-------------------------------------------------------|
| schedule(JobDetail jobDetail, Trigger trigger) | JobDetail과 Trigger를 사용하여 Job을 스케줄링 |
| scheduleJob(JobDetail jobDetail, Trigger trigger) | schedule()과 같이 JobDetail과 Trigger를 사용하여 Job을 스케줄링 |
| scheduleJob(Trigger trigger) | JobDetail 없이 Trigger만 사용하여 Job을 스케줄 |
| rescheduleJob(TriggerKey triggerKey, Trigger newTrigger) | 지정된 Trigger의 스케줄을 업데이트 |
| unscheduleJob(TriggerKey triggerKey) | 지정된 Trigger를 해제하여 Job 스케줄링을 취소 |
| pauseTrigger(TriggerKey triggerKey) | 지정된 Trigger를 일시 중지 |
| resumeTrigger(TriggerKey triggerKey) | 지정된 Trigger를 다시 시작 |
| pauseJob(JobKey jobKey) | 지정된 Job을 일시 중지 |
| resumeJob(JobKey jobKey) | 지정된 Job을 다시 시작 |

<br>

### 2-2-5. Spring boot Quartz 의 실행 주기

| 단계 | 분류 | 설명                                                                                        |
|-----|----------------|-------------------------------------------------------------------------------------------|
| 1 | 스케줄러 초기화 | Quartz 스케줄러는 시작되면 먼저 스케줄러를 초기화함<br>이 초기화 과정에서는 스케줄러에 대한 설정을 로드하고,<br> 자바 애플리케이션 컨텍스트와 연결 |
| 2 | 작업 스케줄링 | Quartz 스케줄러는 작업 스케줄링을 수행함<br>이 과정에서는 사용자가 등록한 작업을 실행할 시간을 계산하여 스케줄링 테이블에 등록              |
| 3 | 작업 실행 | 스케줄링 된 작업이 실행됨<br>Quartz 스케줄러는 스케줄링 된 작업을 실행하기 위해 쓰레드 풀을 사용                           |
| 4 | 작업 완료 | 작업이 완료되면 Quartz 스케줄러는 작업이 완료되었다는 신호를 받음<br>이 신호를 받으면 스케줄링 테이블에서 작업을 제거               |
| 5 | 스케줄러 종료 | Quartz 스케줄러는 애플리케이션 종료 시점에 스케줄러를 종료함<br>이 과정에서는 스케줄링 된 작업을 모두 제거하고, 쓰레드 풀을 종료        |

<br><br>

## 2-3. 테스트 개발환경과 활용 예시

### 2-3-1. 의존성 라이브러리 등록

```groovy
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-quartz'      // Spring Boot Quartz
}
```

<br>

### 2-3-2. 활용 예시 - Trigger과 Scheduler

```java
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
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
```

<br>

### 2-3-2. 활용 예시 - Trigger과 Scheduler

```java
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
// 실행할 Job 클래스
public class MyJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello Quartz!");
    }
}
```

