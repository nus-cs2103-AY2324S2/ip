package Commands;

import Irwyn.Tasks.TaskList;
import Misc.Ui;
import Misc.StorageManager;

/**
 * This is an abstract class for all Commands used in the chatbot.
 *
 * @author  Irwyn Liong
 * @version Week-3
 */
public abstract class Command {
    boolean isExit;

    /**
     * Constructor for a Command.
     * @param isExit Exit status.
     */
    Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the commands.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    public abstract void execute(TaskList taskList, Ui ui, StorageManager storageManager);

    /**
     * Returns the exit status of the command.
     *
     * @return Exit status of the command.
     */
    public boolean isExit() {
        return this.isExit;
    }
}