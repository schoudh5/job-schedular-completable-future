import lombok.Getter;
import lombok.Value;
import strategies.SchedulingStrategy;

@Value
public class SomeWork {

    Runnable runnable;
    SchedulingStrategy schedulingStrategy;

    public SomeWork(SchedulingStrategy schedulingStrategy, Runnable runnable) {
        this.schedulingStrategy = schedulingStrategy;
        this.runnable = runnable;
    }
}
