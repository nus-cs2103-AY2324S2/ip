package tyler.command;

import tyler.task.Deadline;
import tyler.ui.Ui;
import tyler.task.TaskList;
import tyler.storage.Storage;

import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand {
    protected LocalDateTime end;

    public DeadlineCommand(String name, LocalDateTime end) {
        super(name);
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(this.taskName, this.end);
        tasks.addTask(newTask);
        int num = tasks.getNumOfTasks();
        ui.showTaskAdded(newTask, num);
    }
}
