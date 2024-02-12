/*
 * ExitCommand.java
 * This class represents a command to exit the Duke application.
 * It allows the user to terminate the application gracefully.
 */

package duke.command;

import duke.Ui;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a command to exit the Duke application.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
