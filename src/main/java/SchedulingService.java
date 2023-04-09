import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class SchedulingService {
    private BlockingQueue<SomeWork> someWorkBlockingQueue;
    private CompletableFuture<Void>[] doers;
    private ExecutorService executorService;

    public SchedulingService() {
        executorService = Executors.newFixedThreadPool(10);
        this.someWorkBlockingQueue = new PriorityBlockingQueue<>(10,
                Comparator.comparingInt(w -> (int) w.getSchedulingStrategy().getExecutionTime()));

        doers = new CompletableFuture[10];

        for(int i=0;i<10;i++){
            CompletableFuture<Void> doer = CompletableFuture.runAsync(()->{
                while(true){
                    SomeWork someWork = someWorkBlockingQueue.poll();

                    while(someWork!=null && System.currentTimeMillis() < someWork.getSchedulingStrategy().getExecutionTime()){
//                        try {
//                            TimeUnit.SECONDS.sleep(1);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }

                    if(someWork!=null){
                        someWork.getRunnable().run();
                        long nextExecutionTime = someWork.getSchedulingStrategy().getNextExecutionTime();
                        if(nextExecutionTime!=0){
                            someWorkBlockingQueue.offer(someWork);
                        }
                    }


                }

            },executorService);
            doers[i]=doer;
        }

//        CompletableFuture<Void> combinedFuture  = CompletableFuture.allOf(doers);
//        combinedFuture.join();
//
//        executorService.shutdown();
    }

    public void addTasks(SomeWork... works){
        Arrays.stream(works).forEach(w-> this.someWorkBlockingQueue.offer(w));
    }
}
