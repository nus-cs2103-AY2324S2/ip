package duke.command;

import duke.exception.DukeException;
import duke.helpers.FileManaging;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

import java.io.IOException;

/**
 * ByeCommand class
 */
public class ByeCommand extends Command {
    /**
     * Executes bye command.
     *
     * @param tasks List of tasks.
     * @param ui Ui of ai chatbot.
     * @param storage External storage in hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayToScreen("Bye. Hope to see you again soon!");
    }

    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns true to indicate that user is exiting this chatbot.
     *
     * @return true to indicate that user leaving chatroom.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
