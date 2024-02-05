package command;

import roland.Storage;
import roland.TaskList;
import roland.Ui;

public abstract class Command {



    /**
     *
     * @param tasks The taskList that stores the task
     * @param ui The user interface that outputs to the terminal
     * @param storage The storage path to store persistent data
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the program should exit.
     *
     * @return A boolean value indicating whether the program should exit (true) or continue running (false).
     */
    public boolean isExit() {
        return false;
    }
}
