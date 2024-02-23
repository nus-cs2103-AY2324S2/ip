package duke.commands;

import static duke.constants.Constant.DATE_TIME_FORMATTER_FOR_PRINT;

import java.time.LocalDateTime;

import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to create an event task.
 */
public class CreateEventCommand extends Command {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a new CreateEventCommand object with the given description, start, and end date and time.
     *
     * @param description The description of the event task.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public CreateEventCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        Task newTask = null;
        newTask = new Event(description, start, end, DATE_TIME_FORMATTER_FOR_PRINT);
        tasks.addTask(newTask);
        return newTask.displayTask(tasks.size());
    }
}
