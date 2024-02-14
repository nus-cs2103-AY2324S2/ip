package duke.command;
import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    private String description;
    private String by;

    /**
     * Constructs a DeadlineCommand with a description and a deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the DeadlineCommand by creating a new Deadline task, adding it to the task list,
     * displaying the task response using Ui, and saving the updated task list using Storage.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline;
        deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.addResponse(deadline, tasks);
        storage.saveList(tasks.getTasks());
    }
}
