package ghbot.executor;

import ghbot.Event;

public class EventExecutor extends Executor {
    private String description;
    private String fromTime;
    private String toTime;
    public EventExecutor(String description, String fromTime, String toTime) {
        this.description = description;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    @Override
    public void execute() {
        Event event = new Event(this.description, this.fromTime, this.toTime);
        this.taskList.addTask(event);
        System.out.println("Got it. I've added this task:\n" + event);
        System.out.println("Now you have " + this.taskList.taskSize() + " tasks in the list.");
    }
}
