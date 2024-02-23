package mychats.command;
import mychats.exception.MyChatsException;
import mychats.main.Storage;
import mychats.main.TaskList;
import mychats.main.Ui;
import mychats.task.Deadline;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    private String description;
    private String byWhen;

    /**
     * Constructs a DeadlineCommand with a description and a deadline.
     *
     * @param description Description of the task.
     * @param byWhen Deadline of the task.
     */
    public DeadlineCommand(String description, String byWhen) {
        this.description = description;
        this.byWhen = byWhen;
    }

    /**
     * Executes the DeadlineCommand by creating a new Deadline task, adding it to the task list,
     * displaying the task response using Ui, and saving the updated task list using Storage.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     * @throws MyChatsException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MyChatsException {
        Deadline deadline;
        deadline = new Deadline(description, byWhen);
        tasks.add(deadline);
        ui.addResponse(deadline, tasks);
        storage.saveList(tasks.getTasks());
    }
}
