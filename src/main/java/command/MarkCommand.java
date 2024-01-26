package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {

    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task curr = tasks.getTask(taskIndex);
        curr.markAsDone();

        ui.printDivider();
        System.out.println("    Nice! I've marked this task as done: ");
        curr.taskPrinter(taskIndex);
        ui.printDivider();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
