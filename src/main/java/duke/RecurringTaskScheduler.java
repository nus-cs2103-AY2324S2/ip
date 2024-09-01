package duke;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Scheduler for checking and adjusting recurring tasks.
 */
public class RecurringTaskScheduler {
    private MyList myList;
    private ScheduledExecutorService scheduler;

    /**
     * Constructs a RecurringTaskScheduler instance for the given task list.
     *
     * @param myList The list to check for recurring tasks.
     */
    public RecurringTaskScheduler(MyList myList) {
        this.myList = myList;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    /**
     * Starts the scheduler to check and adjust recurring tasks at regular intervals.
     *
     * @param intervalInSeconds The interval between recurring task checks in seconds.
     */
    public void startScheduler(int intervalInSeconds) {
        scheduler.scheduleAtFixedRate(this::checkAndAdjustRecurringTasks, 0, intervalInSeconds, TimeUnit.SECONDS);
    }

    private void checkAndAdjustRecurringTasks() {
        myList.checkAndAdjustRecurringTasks();
    }

    /**
     * Stops the scheduler.
     */
    public void stopScheduler() {
        scheduler.shutdown();
    }
}
