package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class TagCommand extends Command {
    private int taskIndex;
    private String tag;

    /**
     * Constructor for Tag Command.
     *
     * @param taskIndex index of the task to be tagged.
     * @param tag String tag that needs to be tagged.
     * */
    public TagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    /**
     * Adds a tag to the task from the current TaskList.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task curr = tasks.getTask(taskIndex);

        curr.addTag(tag);

        tasks.replaceTask(taskIndex, curr);

        String result = Ui.printDivider()
                + System.lineSeparator()
                + "Noted, I've tagged this task: "
                + System.lineSeparator()
                + curr.taskPrinter()
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
