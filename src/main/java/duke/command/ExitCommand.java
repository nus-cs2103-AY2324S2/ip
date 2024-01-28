package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Exit the Chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Does nothing.
     * @param tasks The list of tasks.
     * @param ui UI interface with the user.
     * @param storage Storage interface for persistence.
     * @return Goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "bye bye!";
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ExitCommand;
    }
}
