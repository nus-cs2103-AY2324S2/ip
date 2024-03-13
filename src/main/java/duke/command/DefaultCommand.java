package duke.command;

import duke.task.TaskList;

import duke.util.Ui;
import duke.util.Storage;

/**
 * The class representing a generic command when an unknown command is entered.
 * */
public class DefaultCommand extends Command {
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "";
    }

    public boolean isExit() {
        return false;
    }
}
