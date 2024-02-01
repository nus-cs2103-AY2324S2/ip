package duke.command;

import duke.TaskList;
import duke.task.Task;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

public class DeleteTaskCommand extends Command {
    private final int taskNum;

    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException{
        assert this.taskNum > 0 : "Input task number cannot be less than 1";
        assert this.taskNum <= tasks.getTasksSize()
                : "Input duke.task.Task number cannot be more than total number of Tasks.";
        Task toDelete = tasks.getTask(this.taskNum - 1);
        tasks.deleteTask(this.taskNum - 1);
        storage.save(tasks);
        ui.showDeleteMessage(toDelete, tasks);
    }

}
