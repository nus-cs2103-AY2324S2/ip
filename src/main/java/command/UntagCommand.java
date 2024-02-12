package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class UntagCommand extends Command {
    private int taskIndex;
    private int tagIndex;

    /**
     * Constructor for Un-tag Command.
     *
     * @param taskIndex The index of the task to be unmarked.
     * */
    public UntagCommand(int taskIndex, int tagIndex) {
        this.taskIndex = taskIndex;
        this.tagIndex = tagIndex;
    }


    /**
     * Un-tags the tag at tagIndex of the task at taskIndex.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task curr = tasks.getTask(taskIndex);
        curr.untag(tagIndex);

        String result = Ui.printDivider()
                + System.lineSeparator()
                + "OK, I've untagged the task : "
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
