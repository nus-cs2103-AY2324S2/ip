package commands;

import irwyn.tasks.TaskList;
import misc.Ui;
import misc.StorageManager;

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
     * Executes the commands and returns a response.
     *
     * @param storageManager Storage manager handling storing & deletion of tasks.
     * @param ui Ui handles output.
     * @param taskList TaskList handles the tasks list.
     * @return Response String.
     */
    public abstract String execute(StorageManager storageManager, Ui ui, TaskList taskList);

    /**
     * Returns the exit status of the command.
     *
     * @return Exit status of the command.
     */
    public boolean isExit() {
        return this.isExit;
    }
}