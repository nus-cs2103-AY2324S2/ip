package duke.command;

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
     * @return Goodbye message.
     */
    @Override
    public String execute(TaskList tasks) {
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
