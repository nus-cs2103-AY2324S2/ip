package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

import java.time.LocalDate;

/**
 * Command to create a new Deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    /**
     * Contructs a deadline command.
     *
     * @param description Description of task.
     * @param by Due date of deadline. LocalDate object.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command, creating a new task, adding it to the list, saving to storage.
     * Also displays messages to user.
     *
     * @param list TaskList object containing current tasks.
     * @param ui To send instructions on how to update the user interface.
     * @param storage To update storage with new deadline task.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task newDeadline = Task.createTask(TaskType.DEADLINE, description, false, by);
        list.add(newDeadline);
        storage.save(list);
        ui.showMessage("added new deadline: " + newDeadline);
        ui.showMessage("Looks like you have " + list.countTasks() + " things left to do!");
    }
}
