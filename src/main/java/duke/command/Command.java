package duke.command;

import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

/**
 * Abstract class Command
 */
public abstract class Command {
    /**
     * Return command string after executing current command.
     *
     * @param tasks List of tasks.
     * @param ui Ui of ai chatbot.
     * @param storage External storage in hard disk.
     * @return command string.
     */
    public abstract String getExecutionMessage(TaskList tasks, Ui ui, Storage storage, Storage secondaryStorage);

    /**
     * Returns false to indicate that user is still in this chatbot.
     *
     * @return false to indicate that user still in chatroom.
     */
    public boolean isExit() {
        return false;
    }
}
