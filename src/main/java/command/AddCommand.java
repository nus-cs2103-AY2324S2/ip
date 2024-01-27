package command;

import task.Task;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

public class AddCommand extends Command {
    private Task task;
    private String saveData;

    public AddCommand(Task task, String saveData) {
        this.task = task;
        this.saveData = saveData;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addTask(this.task);
        storage.writeLine(this.saveData);
        UI.print("Got it. I've added this task:");
        UI.print("\t" + this.task);
        UI.print(String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }

    @Override
    public String getTestData() {
        return saveData;
    }
}
