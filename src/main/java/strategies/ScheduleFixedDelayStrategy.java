package strategies;

import lombok.Getter;

public class ScheduleFixedDelayStrategy implements SchedulingStrategy {
    @Getter
    long executionTime;
    long initialDelay;
    long delayBetweenTasks;

    public ScheduleFixedDelayStrategy(long initialDelay, long delayBetweenTasks) {
        this.initialDelay = initialDelay;
        this.delayBetweenTasks = delayBetweenTasks;
        this.executionTime = initialDelay;
    }

    private void calculateNextExecutionTime() {
        this.executionTime = this.executionTime+delayBetweenTasks;
    }

    @Override
    public long getNextExecutionTime() {
        calculateNextExecutionTime();
        return executionTime;
    }
}
