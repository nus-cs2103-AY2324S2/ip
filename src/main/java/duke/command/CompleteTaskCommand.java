package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

import java.io.IOException;
public class CompleteTaskCommand extends Command {
    private final int taskIndex;

    public CompleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.completeTask(this.taskIndex - 1);
        storage.save(tasks);
        ui.showMarkAsDoneMessage(tasks.getTask(this.taskIndex - 1));
    }
}