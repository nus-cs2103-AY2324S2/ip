package commands;

import util.Ui;
import util.TaskList;
import util.Storage;
import java.time.LocalDateTime;
import tasks.Event;

/**
 * Represents a command to add an event task to the task list.
 * Extends the Command class and implements the execute method to execute the command.
 */
public class EventCommand extends Command {

    private String event;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an EventCommand object with the given event description, start date and time, and end date and time.
     *
     * @param event Description of the event task.
     * @param start Start date and time of the event.
     * @param end   End date and time of the event.
     */
    public EventCommand(String event, LocalDateTime start, LocalDateTime end) {
        this.event = event;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the event command by creating an Event object with the specified details,
     * adding it to the task list, and saving the updated task list to storage.
     *
     * @param taskList The TaskList containing the current tasks.
     * @param ui       The Ui instance for user interaction and output.
     * @param storage  The Storage instance for saving tasks or loading data.
     * @return A UserCommand indicating the success of the event addition operation.
     */
    @Override
    public UserCommand execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(this.event, this.start, this.end);
        taskList.addTask(event);
        storage.saveToFile(taskList);
        return new UserCommand("\tGot it. I've added this task: "
                , "\t" + event, taskList.getTaskSummary());
    }
}
