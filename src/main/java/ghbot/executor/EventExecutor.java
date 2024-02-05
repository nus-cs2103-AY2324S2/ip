package ghbot.executor;

import ghbot.Event;

/**
 * EventExecutor Class.
 * Executes "event" command.
 */
public class EventExecutor extends Executor {
    private String description;
    private String fromTime;
    private String toTime;

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
    }

    /**
     * Prints a string to let user know that the event has been added.
     */
    @Override
    public void execute() {
        Event event = new Event(this.description, this.fromTime, this.toTime);
        this.taskList.addTask(event);
        System.out.println("Got it. I've added this task:\n" + event);
        System.out.println("Now you have " + this.taskList.taskSize() + " tasks in the list.");
    }
}
