package podz.commands;

import podz.task.Event;

/**
 * Represents a command to add an event task in the task manager.
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * Constructs an EventCommand object with the specified event task.
     *
     * @param event the event task to be added.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Executes the event command to add an event task.
     */
    @Override
    public String execute() {
        super.taskList.addTask(event);
        super.response = "Got it. I've added this task:\n"
                        + this.event + "\n"
                        + super.taskList.getListCounter();
        return super.response;
    }
}
