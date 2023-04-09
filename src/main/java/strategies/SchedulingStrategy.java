package strategies;

public interface SchedulingStrategy {
    long getNextExecutionTime();
    long getExecutionTime();
}
