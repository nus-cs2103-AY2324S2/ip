package duke.commands;

import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.constants.Constant.DATE_TIME_FORMATTER_FOR_PRINT;

/**
 * Represents a command to create an event task.
 */
public class createEventCommand extends Command{
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
    public createEventCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        Task newTask = null;
        newTask = new Event(description, start, end, DATE_TIME_FORMATTER_FOR_PRINT);
        tasks.addTask(newTask);
        ui.showLine();
        newTask.displayTask(tasks.size());
        ui.showLine();
        return true;
    }


}
