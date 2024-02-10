package commands;

import util.Ui;
import util.TaskList;
import util.Storage;
import tasks.Deadline;
import java.time.LocalDateTime;

/**
 * The DeadlineCommand class represents a command to add a deadline task.
 * It extends the Command class and implements the execute method to execute the command.
 */
public class DeadlineCommand extends Command {

    private String taskDescription;
    private LocalDateTime due;

    /**
     * Constructs a DeadlineCommand object with the given task description and due date.
     *
     * @param taskDescription The description of the deadline task.
     * @param due             The due date and time of the deadline task.
     */
    public DeadlineCommand(String taskDescription, LocalDateTime due) {
        this.taskDescription = taskDescription;
        this.due = due;
    }

    /**
     * Executes the deadline command by creating a new Deadline task with the given description and due date,
     * adding it to the task list, and saving the updated task list to storage.
     *
     * @param taskList The TaskList containing the current tasks.
     * @param ui       The Ui instance for user interaction and output.
     * @param storage  The Storage instance for saving tasks or loading data.
     */
    @Override
    public UserCommand execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.taskDescription, this.due);
        taskList.addTask(deadline);
        storage.saveToFile(taskList);
        return new UserCommand("\tAdded deadline: ", "\t" + deadline, taskList.getTaskSummary());
    }
}

