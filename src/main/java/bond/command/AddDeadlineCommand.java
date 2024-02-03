package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.DeadlineTask;
import bond.task.Task;
import bond.task.TaskList;

public class AddDeadlineCommand extends AddCommand {

    private String deadline;

    public AddDeadlineCommand(String taskName, String deadline) {
        super("deadline", taskName);
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        Task newTask = new DeadlineTask(super.taskName, this.deadline);
        tasks.addTask(newTask);
        ui.taskAdded(newTask, tasks);
        storage.storeTask(newTask, tasks);
    }

}
