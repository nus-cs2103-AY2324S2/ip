package duke.command;

import duke.common.TaskList;
import duke.task.Deadline;
import duke.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a deadline command initiated by the user. <code>DeadLineCommand</code> would creates a deadline task
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_USAGE = "deadline: it creates a deadline task \n" +
            "Example: deadline SU 2103T /by 2024-02-06 18:00";
    private String description;
    private LocalDateTime deadline;

    /**
     * Constructor of the deadline task
     * @param description description of the deadline task
     * @param deadline deadline of the task
     */
    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Creates a deadline task, save it to the task list and then display the result to the user
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadlineTask = new Deadline(description, false, deadline);
        taskList.addTask(deadlineTask);
        ui.showNewTask(deadlineTask, taskList);
    }
}
