package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {

    private int taskIndex;

    /**
     * Constructor for Mark Command.
     *
     * @param taskIndex The index of the task to be marked.
     * */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task at taskIndex of tasks as done.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task curr = tasks.getTask(taskIndex);
        curr.markAsDone();

        String result = Ui.printDivider()
                + System.lineSeparator()
                + "Nice! I've marked this task as done: "
                + System.lineSeparator()
                + curr.taskPrinter(taskIndex)
                + System.lineSeparator()
                + Ui.printDivider();

        storage.storeToLocal(tasks);

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
