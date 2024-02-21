package command;

import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    protected String description;
    protected LocalDateTime deadline;
    protected boolean isDone;


    /**
     * Constructor for Deadline Command.
     *
     * @param description Description of Deadline.
     * @param deadline LocalDateTime object of deadline date/time of Deadline.
     * */
    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
        this.isDone = false;
    }

    /**
     * Constructor for Deadline Command.
     *
     * @param description Description of Deadline.
     * @param deadline LocalDateTime object of deadline date/time of Deadline.
     * @param isDone Boolean value if Deadline is done.
     * */
    public DeadlineCommand(String description, LocalDateTime deadline, boolean isDone) {
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    /**
     * Creates a Deadline & adds to the current TaskList.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, isDone, deadline);
        tasks.addToList(task);

        return ui.addedTaskPrinter(task, tasks.taskNum());
    }

    /**
     * Informs if this command is an Exit command.
     *
     * @return Boolean value of true if this command is an exit command.
     * */
    @Override
    public boolean isExit() {
        return false;
    }
}
