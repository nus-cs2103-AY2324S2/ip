package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;
import fireraya.task.Deadline;
import fireraya.task.Task;

public class DeadlineCommand extends Command {

    private String task;
    private String deadline;

    public DeadlineCommand(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
        Deadline d = new Deadline(task, deadline);
        tasks.add(d);
        ui.taskAddedMsg(d, tasks.size());
        storage.saveToFile(tasks.getTasks());
    }

}
