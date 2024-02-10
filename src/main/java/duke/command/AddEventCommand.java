package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.Event;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the Command of adding a new event to a task list.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    /**
     * Initializes a Command to add a event using the input description and dates.
     *
     * @param type the type of the command which is event.
     * @param description the description of the event to be added.
     * @param fromDate the starting date of the event to be added.
     * @param toDate the ending date of the event to be added.
     */
    public AddEventCommand(Parser.Cmd type, String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(type);
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Adds a new event task to give taskList.
     *
     * @param taskList the given taskList to add the task to.
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
        Event event = new Event(this.description, this.fromDate, this.toDate);
        String fromTime = this.fromDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        String toTime = this.toDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        String[] data = {this.description, fromTime, toTime};
        taskList.addTask(event, "event", data);
        ui.informItemAdded(event, taskList);
    }
}
