package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to be executed by Duke.
 */
public class Command {

    /**
     * Constructs a new Command object.
     */
    public Command() {
    }

    /**
     * Executes the command without UI or TaskList arguments.
     *
     * @return true if the command is executed successfully, false otherwise.
     */
    public String execute() {
        return "";
    }

    public String execute(String str) {
        return "";
    }

    /**
     * Executes the command with UI and TaskList arguments.
     *
     * @param ui The user interface to interact with the user.
     * @param tasks The task list to perform operations on.
     * @return true if the command is executed successfully, false otherwise.
     */
    public String execute(Ui ui, TaskList tasks) {
        return "";
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

