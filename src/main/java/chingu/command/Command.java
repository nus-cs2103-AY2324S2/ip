package chingu.command;

import chingu.Storage;
import chingu.task.TaskList;
import chingu.Ui;

public abstract class Command {

    /**
     * Executes the Command by the user
     *
     * @param tasks that contains current list of tasks
     * @param ui that handles the Ui of the Chingu Program
     * @param storage that deals with loading or saving the file
     * @return the response of the Chingu program from the command
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "This is an unknown Command!";
    }

    /**
     * Checks if it is last command of the Program (Exit Command - "bye")
     *
     * @return boolean that indicate if it is the last command from user
     */
    public boolean isExit() {
        return false;
    }

}
