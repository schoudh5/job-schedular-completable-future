import strategies.ScheduleFixedDelayStrategy;
import strategies.ScheduleFixedRateStrategy;
import strategies.SimpleSchedulingStrategy;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("task1");
        Runnable r2 = () -> System.out.println("task2");
        Runnable r3 = () -> System.out.println("task3");

        long currentTimeInMillis = System.currentTimeMillis();
        System.out.println(currentTimeInMillis);

        SimpleSchedulingStrategy simpleSchedulingStrategy = new SimpleSchedulingStrategy(10, TimeUnit.SECONDS, currentTimeInMillis);
        SomeWork someWork1 = new SomeWork(simpleSchedulingStrategy, r1);
        ScheduleFixedRateStrategy scheduleFixedRateStrategy = new ScheduleFixedRateStrategy(10, 1, TimeUnit.SECONDS, currentTimeInMillis);
        SomeWork someWork2 = new SomeWork(scheduleFixedRateStrategy, r2);

        SchedulingService schedulingService = new SchedulingService();
        schedulingService.addTasks(someWork1, someWork2);

    }
}
