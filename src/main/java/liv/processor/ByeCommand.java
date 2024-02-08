package liv.processor;

import liv.exception.LivException;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * Represents the command that exit the chatbot.
 */
public class ByeCommand extends Command {
    /**
     * Exit the chatbot.
     * @param tasks The list of tasks to operate on.
     * @param ui The Ui to gives interaction with users.
     * @throws LivException
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws LivException {
        Ui.displayByeCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
