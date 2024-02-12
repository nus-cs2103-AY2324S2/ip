package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private String description;

    private LocalDate by;

    /**
     * Constructs a DeadlineCommand with the specified input, description, and due date.
     *
     * @param input       The input string associated with the command.
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task.
     */
    public DeadlineCommand(String input, String description, LocalDate by) {
        super(input);
        this.description = description;
        this.by = by;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        int counter = tasks.getCounter();

        Task t = new Deadline(description, by);
        tasks.addTask(t);

        return ui.showAddTaskMessage(t, counter);
    }
}
