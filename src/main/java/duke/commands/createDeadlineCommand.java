package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.constants.Constant.DATE_TIME_FORMATTER_FOR_PRINT;

/**
 * Represents a command to create a deadline task.
 */
public class createDeadlineCommand extends Command{
    private String description;
    private LocalDateTime deadline;


    /**
     * Constructs a new CreateDeadlineCommand object with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline date and time of the task.
     */
    public createDeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        Task newTask = null;
        newTask = new Deadline(description, deadline, DATE_TIME_FORMATTER_FOR_PRINT);
        tasks.addTask(newTask);
        ui.showLine();
        newTask.displayTask(tasks.size());
        ui.showLine();
        return true;
    }


}
