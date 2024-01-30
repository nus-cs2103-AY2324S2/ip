package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructor for Delete Command.
     *
     * @param taskIndex index of the task to be deleted.
     * */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task from the current TaskList.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task curr = tasks.getTask(taskIndex);
        tasks.removeFromList(taskIndex);

        Ui.printDivider();
        System.out.println("    Noted, I've removed this task: ");
        curr.taskPrinter();
        Ui.printDivider();
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
