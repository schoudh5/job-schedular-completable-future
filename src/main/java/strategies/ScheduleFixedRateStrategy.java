package strategies;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

public class ScheduleFixedRateStrategy implements SchedulingStrategy {

    long initialDelay;
    long period;
    @Getter
    long executionTime;
    long times;
    TimeUnit timeunit;
    long currentTime;

    public ScheduleFixedRateStrategy(long initialDelay, long period, TimeUnit timeUnit, long currentTime) {

        long delayMilli = TimeUnit.MILLISECONDS.convert(initialDelay, timeUnit);

        this.initialDelay = initialDelay;
        this.period = period;
        this.executionTime = currentTime+delayMilli;
        this.timeunit = timeUnit;
        this.currentTime = currentTime;
        times = 0;
    }


    private void calculateNextExecutionTime() {
        times = times+1;
        this.executionTime = currentTime+ TimeUnit.MILLISECONDS.convert(initialDelay+times*period, timeunit);
    }

    @Override
    public long getNextExecutionTime() {
        calculateNextExecutionTime();
        return executionTime;
    }
}
