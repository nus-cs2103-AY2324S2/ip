package ghbot.executor;

import ghbot.task.Event;

/**
 * EventExecutor Class.
 * Executes "event" instruction.
 */
public class EventExecutor extends Executor {
    private String description;
    private String fromTime;
    private String toTime;
    private String executeStr;

    /**
     * EventExecutor Constructor.
     * @param description A description of the event.
     * @param fromTime The start time of the event.
     * @param toTime The end time of the event.
     */
    public EventExecutor(String description, String fromTime, String toTime) {
        this.description = description;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.executeStr = "";
    }

    /**
     * Returns a string to let user know that the event has been added.
     * @return A string to let user know that the event has been added.
     */
    @Override
    public String execute() {
        Event event = new Event(this.description, this.fromTime, this.toTime);
        taskList.addTask(event);
        this.executeStr = "Got it. I've added this task:\n" + event + "\n";
        this.executeStr = this.executeStr + "Now you have " + taskList.taskSize() + " tasks in the list.";
        return this.executeStr;
    }
}
