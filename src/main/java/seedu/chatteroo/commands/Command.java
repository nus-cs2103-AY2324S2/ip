package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.Task;
import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Handles the execution of the command.
 */
public class Command {
    private int taskNum;
    private Task newTask;
    private String keyword;

    /**
     * Constructor for the Command class.
     * @param taskNum The task number.
     */
    public Command(int taskNum) {
        this.taskNum = taskNum;
    }
    /**
     * Constructor for the Command class.
     * @param task The task to be added.
     */
    public Command(Task task) {
        this.newTask = task;
    }
    /**
     * Constructor for the Command class.
     * @param keyword The keyword to be searched.
     */
    public Command(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Constructor for the Command class.
     */
    public Command() {
    }
    /**
     * Executes the specified command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }


}

