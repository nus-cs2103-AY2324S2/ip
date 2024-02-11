package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Exit the Chatbot.
 */
public class ExitCommand extends Command {

    public static final String BYE_BYE = "bye bye!";
    /**
     * Does nothing.
     *
     * @param tasks   The list of tasks.
     * @param storage Storage interface for persistence.
     * @return Goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        return BYE_BYE;
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
