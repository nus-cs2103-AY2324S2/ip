package podz.commands;

import podz.task.Event;
import podz.ui.Ui;

/**
 * Represents a command to add an event task in the task manager.
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * Constructs an EventCommand object with the specified event task.
     *
     * @param event the event task to be added
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Executes the event command to add an event task.
     *
     * @param ui the user interface that interacts with the user
     */
    @Override
    public void execute(Ui ui) {
        super.taskList.addTask(event);
        ui.printToUser("\tGot it. I've added this task:",
                        "\t" + this.event,
                        super.taskList.getListCounter());
    }
}
