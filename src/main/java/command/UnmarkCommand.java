package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructor for Un-mark Command.
     *
     * @param taskIndex The index of the task to be unmarked.
     * */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }


    /**
     * Un-marks the task at taskIndex of tasks as done.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task curr = tasks.getTask(taskIndex);
        curr.markAsUndone();

        String result = Ui.printDivider()
                + System.lineSeparator()
                + "OK, I've marked this task as not done : "
                + System.lineSeparator()
                + curr.taskPrinter(taskIndex)
                + System.lineSeparator()
                + Ui.printDivider();

        return result;
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
