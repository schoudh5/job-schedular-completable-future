package strategies;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

public class SimpleSchedulingStrategy implements SchedulingStrategy {

    @Getter
    private long executionTime;
    long currentTime;

    public SimpleSchedulingStrategy(long delay, TimeUnit timeUnit,long currentTime) {
        this.currentTime = currentTime;
        long millDelay = TimeUnit.MILLISECONDS.convert(delay, timeUnit);
        this.executionTime = currentTime + millDelay;
    }

    private void calculateNextExecutionTime() {
        executionTime = 0;
    }

    @Override
    public long getNextExecutionTime() {
        calculateNextExecutionTime();
        return executionTime;
    }
}
