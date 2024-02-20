package duke.commands;

import static duke.constants.Constant.DATE_TIME_FORMATTER_FOR_PRINT;

import java.time.LocalDateTime;

import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
/**
 * Represents a command to create a deadline task.
 */
public class CreateDeadlineCommand extends Command {
    private String description;
    private LocalDateTime deadline;


    /**
     * Constructs a new CreateDeadlineCommand object with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline date and time of the task.
     */
    public CreateDeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        Task newTask = null;
        newTask = new Deadline(description, deadline, DATE_TIME_FORMATTER_FOR_PRINT);
        tasks.addTask(newTask);
        return newTask.displayTask(tasks.size());
    }
}
